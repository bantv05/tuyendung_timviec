package org.example.bejobs.repository;

import jakarta.transaction.Transactional;
import org.example.bejobs.model.user.InfoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInfoUserRepository extends JpaRepository<InfoUser, Integer> {
    Optional<InfoUser> findByEmail(String email);
    @Modifying
    @Transactional
    Boolean existsByEmail(String email);
}
