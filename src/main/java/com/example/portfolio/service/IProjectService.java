package com.example.portfolio.service;

import com.example.portfolio.entity.Project;
import java.util.List;

public interface IProjectService {

    public List<Project> getProjects();

    public Project findByProjectId(Long person_id, Long project_id);

    public List<Project> findByPersonId(Long person_id);

    public Project createProject(Long person_id, Project project);

    public Project updateProject(Long person_id, Long project_id, Project updatedProject);

    public void deleteProject(Long person_id, Long project_id);
    
    public void deleteAllProjectsFromPerson(Long person_id);

}
