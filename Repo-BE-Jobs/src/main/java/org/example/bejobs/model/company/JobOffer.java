package org.example.bejobs.model.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder(toBuilder = true)
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJobOffer;
    private String jobTitle;
    private String minSalary;
    private String maxSalary;
    private String technologies;
    private String imageList;
    private String description;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
    @ManyToOne
    @JoinColumn(name = "work_type_id")
    private WorkType workType;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyInformation companyInformation;
    @OneToMany(mappedBy = "jobOffer")
    private List<Application> applications;
}
