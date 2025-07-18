package org.example.bejobs.repository;

import org.example.bejobs.model.company.CompanyInformation;
import org.example.bejobs.model.company.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILevelRepository extends JpaRepository<Level, Integer> {
}
