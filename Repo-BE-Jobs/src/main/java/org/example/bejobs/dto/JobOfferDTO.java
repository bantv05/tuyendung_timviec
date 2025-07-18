package org.example.bejobs.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bejobs.model.company.Application;
import org.example.bejobs.model.company.CompanyInformation;
import org.example.bejobs.model.company.Level;
import org.example.bejobs.model.company.WorkType;

import java.util.List;

@Setter
@Getter
public class JobOfferDTO {
    private Integer idJobOffer;
    private String jobTitle;
    private String minSalary;
    private String maxSalary;
    private String technologies;
    private String imageList;
    private String description;
    private Level level;
    private WorkType workType;
    private CompanyInformation companyInformation;
}
