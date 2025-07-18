package org.example.bejobs.repository;

import org.example.bejobs.model.company.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends JpaRepository<Application, Integer> {
}
