package com.example.portfolio.repository;

import com.example.portfolio.entity.Experience;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    public List<Experience> findByPersonId(Long person_id);

}
