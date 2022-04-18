package com.example.portfolio.security.repository;

import com.example.portfolio.security.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
    User findUserIdByUsername(String username);
    
    User findPersonIdByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}