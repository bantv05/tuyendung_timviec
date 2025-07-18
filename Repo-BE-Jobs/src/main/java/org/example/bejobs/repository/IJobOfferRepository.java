package org.example.bejobs.repository;

import org.example.bejobs.model.company.CompanyInformation;
import org.example.bejobs.model.company.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobOfferRepository extends JpaRepository<JobOffer, Integer> {
    void deleteById(Integer id);
    @Query("SELECT j FROM JobOffer j WHERE " +
            "(:jobTitle IS NULL OR j.jobTitle LIKE %:jobTitle%) AND " +
            "(:cities IS NULL OR j.companyInformation.city IN :cities)")
    List<JobOffer> searchByTitleAndCities(@Param("jobTitle") String jobTitle,
                                          @Param("cities") List<String> cities);
}
