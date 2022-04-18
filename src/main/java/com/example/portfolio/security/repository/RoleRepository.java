package com.example.portfolio.security.repository;

import com.example.portfolio.security.entity.Role;
import com.example.portfolio.security.enums.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName roleName);
    
}
