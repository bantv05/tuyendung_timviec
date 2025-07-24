package org.example.bejobs.service.impl;

import org.example.bejobs.dto.JobOfferDTO;
import org.example.bejobs.dto.responses.JobSearchCriteriaDTO;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.model.company.JobOffer;
import org.example.bejobs.repository.IJobOfferRepository;
import org.example.bejobs.service.IJobOfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobOfferService implements IJobOfferService {
    @Autowired
    private IJobOfferRepository iJobOfferRepository;
    @Autowired
    private ModelMapper modelMapper;
    private static final List<String> ALLOWED_CITIES =
            Arrays.asList("Đà Nẵng", "Hồ Chí Minh", "Hà Nội");

    public List<JobOffer> searchJobOffers(JobSearchCriteriaDTO criteria) {
        if (criteria.getCities() != null && !criteria.getCities().isEmpty()) {
            validateCities(criteria.getCities());
            List<String> filteredCities = criteria.getCities().stream()
                    .filter(ALLOWED_CITIES::contains)
                    .collect(Collectors.toList());
            criteria.setCities(filteredCities.isEmpty() ? null : filteredCities);
        }

        return iJobOfferRepository.searchByTitleAndCities(
                criteria.getJobTitle(),
                criteria.getCities()
        );
    }
    private void validateCities(List<String> cities) {
        if (cities.stream().anyMatch(c -> !ALLOWED_CITIES.contains(c))) {
            throw new IllegalArgumentException(
                    "Invalid city. Allowed values: " + ALLOWED_CITIES
            );
        }
    }

    @Override
    public Page<JobOfferDTO> findAll(Integer numberPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        Page<JobOffer> jobOffers = iJobOfferRepository.findAll(pageable);
        List<JobOfferDTO> jobOfferList = new ArrayList<>();
        for (JobOffer item: jobOffers){
            JobOfferDTO jobOfferDTO = modelMapper.map(item, JobOfferDTO.class);
            jobOfferList.add(jobOfferDTO);
        }
        return new PageImpl<>(jobOfferList, pageable, jobOffers.getTotalElements());
    }

    @Override
    public JobOffer createJobOffer(JobOfferDTO jobOfferDTO) {
        JobOffer jobOffer = modelMapper.map(jobOfferDTO, JobOffer.class);
        return iJobOfferRepository.save(jobOffer);
    }

    @Override
    public JobOffer updateJobOffer(Integer id, JobOfferDTO jobOfferDTO) throws DataNotFoundException {
        JobOffer jobOffer = iJobOfferRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Cannot jobOffer with id"+id));
        JobOffer offer = jobOffer.toBuilder()
                .idJobOffer(jobOffer.getIdJobOffer())
                .jobTitle(jobOfferDTO.getJobTitle())
                .level(jobOfferDTO.getLevel())
                .maxSalary(jobOfferDTO.getMaxSalary())
                .minSalary(jobOfferDTO.getMinSalary())
                .technologies(jobOfferDTO.getTechnologies())
                .imageList(jobOfferDTO.getImageList())
                .description(jobOfferDTO.getDescription())
                .workType(jobOffer.getWorkType())
                .companyInformation(jobOfferDTO.getCompanyInformation())
                .build();
        return iJobOfferRepository.save(offer);
    }

    @Override
    public void deleteJobOffer(Integer id) {
        iJobOfferRepository.deleteById(id);
    }

    @Override
    public JobOfferDTO detailJobOffer(Integer id) {
        JobOffer jobOffer = iJobOfferRepository.findById(id)
                .orElseThrow(() -> new DateTimeException("Cannot job offer with id:" +id));
        return modelMapper.map(jobOffer, JobOfferDTO.class);
    }
}
