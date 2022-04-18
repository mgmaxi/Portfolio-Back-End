package com.example.portfolio.repository;

import com.example.portfolio.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findByPersonId(Long person_id);

}
