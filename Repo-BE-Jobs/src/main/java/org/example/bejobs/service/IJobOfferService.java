package org.example.bejobs.service;

import org.example.bejobs.dto.JobOfferDTO;
import org.example.bejobs.dto.responses.JobSearchCriteriaDTO;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.model.company.JobOffer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobOfferService {
    List<JobOffer> searchJobOffers(JobSearchCriteriaDTO criteria);
    Page<JobOfferDTO> findAll(Integer numberPage, Integer pageSize);
    JobOffer createJobOffer(JobOfferDTO jobOfferDTO);
    JobOffer updateJobOffer(Integer id, JobOfferDTO jobOfferDTO) throws DataNotFoundException;
    void deleteJobOffer(Integer id);
    JobOfferDTO detailJobOffer(Integer id);

}
