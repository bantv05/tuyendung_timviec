package org.example.bejobs.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.bejobs.dto.JobOfferDTO;
import org.example.bejobs.dto.responses.JobSearchCriteriaDTO;
import org.example.bejobs.model.company.JobOffer;
import org.example.bejobs.service.IJobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Transactional
@RestController
@RequestMapping("${api.prefix}")
public class JobOfferController {
    @Autowired
    private IJobOfferService iJobOfferService;

    @PostMapping("job-offers/search")
    public ResponseEntity<List<JobOffer>> searchJobs(
            @RequestBody JobSearchCriteriaDTO criteria) {
        List<JobOffer> results = iJobOfferService.searchJobOffers(criteria);
        return ResponseEntity.ok(results);
    }

    @GetMapping("public/job-offer")
    public ResponseEntity<Page<JobOfferDTO>> getFindAll(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){
        Page<JobOfferDTO> jobOfferDTOS = iJobOfferService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok(jobOfferDTOS);
    }
    @GetMapping("public/job-offer/{id}")
    public ResponseEntity<JobOfferDTO> detailJobOffer(@PathVariable Integer id){
        JobOfferDTO jobOfferDTO = iJobOfferService.detailJobOffer(id);
        return ResponseEntity.ok(jobOfferDTO);
    }

    @PostMapping("public/job-offer/create")
    public ResponseEntity<?> createSubmitOffer(@Valid @RequestBody JobOfferDTO jobOfferDTO, BindingResult result) {
        try {
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            JobOffer jobOffer = iJobOfferService.createJobOffer(jobOfferDTO);
            return ResponseEntity.ok(jobOffer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("public/job-offer/update/{id}")
    public ResponseEntity<?> updateSubmitOffer(@PathVariable Integer id, @RequestBody JobOfferDTO jobOfferDTO, BindingResult result) {
        try {
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            JobOffer jobOffer = iJobOfferService.updateJobOffer(id, jobOfferDTO);
            return ResponseEntity.ok(jobOffer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @DeleteMapping("public/job-offer/{id}")
    public void deleteById(@PathVariable Integer id) {
        iJobOfferService.deleteJobOffer(id);
    }
}
