package com.example.portfolio.repository;

import com.example.portfolio.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    Boolean existsByName(String name);

}
