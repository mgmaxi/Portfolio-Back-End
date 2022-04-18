package com.example.portfolio.security.service;

import com.example.portfolio.security.entity.Role;
import com.example.portfolio.security.enums.RoleName;
import com.example.portfolio.security.repository.RoleRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRep;

    public Optional<Role> findByRoleName(RoleName roleName) {
        return roleRep.findByRoleName(roleName);
    }

    public void save(Role role) {
        roleRep.save(role);
    }

}
