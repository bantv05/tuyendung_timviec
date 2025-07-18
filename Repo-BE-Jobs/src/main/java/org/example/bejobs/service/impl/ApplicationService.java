package org.example.bejobs.service.impl;

import org.example.bejobs.dto.*;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.model.company.Application;
import org.example.bejobs.model.company.JobOffer;
import org.example.bejobs.model.company.Status;
import org.example.bejobs.model.user.InfoUser;
import org.example.bejobs.repository.IApplicationRepository;
import org.example.bejobs.repository.IInfoUserRepository;
import org.example.bejobs.repository.IJobOfferRepository;
import org.example.bejobs.service.IApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private IApplicationRepository iApplicationRepository;
    @Autowired
    private IJobOfferRepository iJobOfferRepository;
    @Autowired
    private IInfoUserRepository iInfoUserRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final String uploadDir = "path/to/upload/directory";
    @Override
    public Page<ApplicationDTO> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Application> applications = iApplicationRepository.findAll(pageable);
        List<ApplicationDTO> applicationDTOS =new ArrayList<>();
        for (Application item: applications){
            ApplicationDTO applicationDTO = modelMapper.map(item, ApplicationDTO.class);
            JobOfferDTO jobOfferDTO = modelMapper.map(item.getJobOffer(), JobOfferDTO.class);
            InfoUserDTO infoUserDTO = modelMapper.map(item.getInfoUser(), InfoUserDTO.class);
            StatusDTO statusDTO = modelMapper.map(item.getStatus(), StatusDTO.class);
            applicationDTO.setStatusDTO(statusDTO);
            applicationDTO.setInfoUserDTO(infoUserDTO);
            applicationDTO.setJobOfferDTO(jobOfferDTO);
            applicationDTOS.add(applicationDTO);
        }
        return new PageImpl<>(applicationDTOS, pageable, applications.getTotalElements());
    }

    @Override
    public void applyForJob(ApplicationRequestDTO applicationRequestDTO, Integer jobOfferId, Integer userId, MultipartFile cvFile) throws IOException {
        String fileName = cvFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(cvFile.getInputStream(), filePath);
        // Create Application entity
        Application application = new Application();
        application.setApplicationDate(LocalDate.now());
        // Find JobOffer and User
        JobOffer jobOffer = iJobOfferRepository.findById(jobOfferId).orElseThrow(() -> new RuntimeException("Job offer not found"));
        InfoUser user = iInfoUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User  not found"));
        application.setJobOffer(jobOffer);
        application.setInfoUser (user);
        application.setFileCV(fileName);
        // Save application
        iApplicationRepository.save(application);
    }


    @Override
    public Application updateApplication(Integer id, StatusDTO statusDTO) throws DataNotFoundException {
        Application application = iApplicationRepository.findById(id)
                .orElseThrow(() -> new  DataNotFoundException ("Cannot application with id + id"));
        Status status = modelMapper.map(statusDTO, Status.class);
        application.setStatus(status);
        return iApplicationRepository.save(application);
    }
}
