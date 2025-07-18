package org.example.bejobs.repository.user;

import org.example.bejobs.model.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCode(String role);
}
