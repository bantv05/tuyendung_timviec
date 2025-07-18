package org.example.bejobs.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.bejobs.dto.ApplicationDTO;
import org.example.bejobs.dto.CompanyInformationDTO;
import org.example.bejobs.model.company.CompanyInformation;
import org.example.bejobs.service.ICompanyInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Transactional
@RestController
@RequestMapping("${api.prefix}")
public class CompanyInformationController {
    @Autowired
    private ICompanyInformationService iCompanyInformationService;
    @GetMapping("public/company-info")
    public ResponseEntity<Page<CompanyInformationDTO>> getFindAll(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){
        Page<CompanyInformationDTO> companyDTOS = iCompanyInformationService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok(companyDTOS);
    }

    @PostMapping("public/company-info/create")
    public ResponseEntity<?> createSubmitCompany(@Valid @RequestBody CompanyInformationDTO companyInformationDTO, BindingResult result) {
        try {
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            CompanyInformation information = iCompanyInformationService.createCompanyInformation(companyInformationDTO);
            return ResponseEntity.ok(information);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("public/company-info/update/{id}")
    public ResponseEntity<?> updateSubmitCompany(@PathVariable Integer id, @RequestBody CompanyInformationDTO companyInformationDTO, BindingResult result) {
        try {
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            CompanyInformation information = iCompanyInformationService.updateCompanyInformation(id, companyInformationDTO);
            return ResponseEntity.ok(information);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
