package com.example.portfolio.repository;

import com.example.portfolio.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    
    Boolean existsByName(String name);

}
