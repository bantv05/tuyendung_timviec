package org.example.bejobs.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.example.bejobs.model.company.JobOffer;

import java.util.List;

@Setter
@Getter
public class CompanyInformationDTO {
    private Integer idCompany;
    private String companyName;
    private String logo;
    private String city;
    private String address;
    private String companyModel;
    private String companySize;
    private String workingHours;
    private String overtimePolicy;
    private String email ;
    private String phoneNumber;
    private String detailedDescription;
    private List<JobOffer> jobOffers;
}
