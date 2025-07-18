package org.example.bejobs.service.impl;

import org.example.bejobs.dto.CompanyInformationDTO;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.model.company.CompanyInformation;
import org.example.bejobs.repository.ICompanyInformationRepository;
import org.example.bejobs.service.ICompanyInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyInformationService implements ICompanyInformationService {

    @Autowired
    private ICompanyInformationRepository iCompanyInformationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CompanyInformationDTO> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CompanyInformation> companyInformation = iCompanyInformationRepository.findAll(pageable);
        List<CompanyInformationDTO> companyInformationDTOS = new ArrayList<>();
        for (CompanyInformation information: companyInformation) {
            CompanyInformationDTO companyInformationDTO = modelMapper.map(information, CompanyInformationDTO.class);
            companyInformationDTOS.add(companyInformationDTO);
        }
        return new PageImpl<>(companyInformationDTOS, pageable, companyInformation.getTotalElements());
    }

    @Override
    public CompanyInformation createCompanyInformation(CompanyInformationDTO companyInformationDTO) {
        CompanyInformation information = modelMapper.map(companyInformationDTO, CompanyInformation.class);
        information = iCompanyInformationRepository.save(information);
        return information;
    }

    @Override
    public CompanyInformation updateCompanyInformation(Integer id, CompanyInformationDTO companyInformationDTO) throws DataNotFoundException {
        CompanyInformation companyToUpdate = iCompanyInformationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Company not found with id: " + id));
        CompanyInformation updatedCompany = companyToUpdate.toBuilder()
                .companyName(companyInformationDTO.getCompanyName())
                .logo(companyInformationDTO.getLogo())
                .city(companyInformationDTO.getCity())
                .address(companyInformationDTO.getAddress())
                .companyModel(companyInformationDTO.getCompanyModel())
                .companySize(companyInformationDTO.getCompanySize())
                .workingHours(companyInformationDTO.getWorkingHours())
                .overtimePolicy(companyInformationDTO.getOvertimePolicy())
                .email(companyInformationDTO.getEmail())
                .phoneNumber(companyInformationDTO.getPhoneNumber())
                .detailedDescription(companyInformationDTO.getDetailedDescription())
                .build();

        return iCompanyInformationRepository.save(updatedCompany);
    }
}
