package org.example.bejobs.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.bejobs.dto.ApplicationDTO;
import org.example.bejobs.dto.ApplicationRequestDTO;
import org.example.bejobs.model.company.Application;
import org.example.bejobs.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@Transactional
@RestController
@RequestMapping("${api.prefix}")
public class ApplicationController {
    @Autowired
    private IApplicationService iApplicationService;

    @GetMapping("public/application")
    public ResponseEntity<Page<ApplicationDTO>> getFindAll(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){
        Page<ApplicationDTO> applicationDTOS = iApplicationService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok(applicationDTOS);
    }

    @PostMapping("apply/{jobOfferId}/{userId}")
    public ResponseEntity<String> applyForJob(
            @PathVariable Integer jobOfferId,
            @PathVariable Integer userId,
            @RequestParam("application") @Valid ApplicationRequestDTO applicationRequestDTO,
            @RequestParam("cvFile") MultipartFile cvFile) {
        try {
            iApplicationService.applyForJob(applicationRequestDTO, jobOfferId, userId, cvFile);
            return ResponseEntity.status(HttpStatus.CREATED).body("Application submitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting application: " + e.getMessage());
        }
    }
}
