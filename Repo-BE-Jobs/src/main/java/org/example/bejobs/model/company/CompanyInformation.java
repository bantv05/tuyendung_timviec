package org.example.bejobs.model.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder(toBuilder = true)//Khi dùng toBuilder(), nó sẽ tạo một bản copy của entity hiện có
//Đảm bảo bạn không thay đổi trạng thái của entity trước khi lưu
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class CompanyInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "companyInformation")
    @JsonIgnore
    private List<JobOffer> jobOffers;
}
