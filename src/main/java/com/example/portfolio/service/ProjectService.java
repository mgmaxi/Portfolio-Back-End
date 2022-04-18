package com.example.portfolio.service;

import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.Project;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.repository.ProjectRepository;
import com.example.portfolio.exceptions.PortfolioAppException;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projRep;

    @Autowired
    private PersonRepository perRep;

    @Override
    public List<Project> getProjects() {
        return projRep.findAll();
    }

    @Override
    public Project findByProjectId(Long project_id, Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Project project = projRep.findById(project_id).orElseThrow(() -> new ResourceNotFoundException("Project", "id", project_id));
        if ((person.getProjects() == null) || !(project.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The project " + project.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        } else {
            return project;
        }
    }

    @Override
    public List<Project> findByPersonId(Long person_id) {
        perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        return projRep.findByPersonId(person_id);
    }

    @Override
    public Project createProject(Long person_id, Project project) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        project.setPerson(person);
        return projRep.save(project);
    }

    @Override
    public Project updateProject(Long person_id, Long project_id, Project updatedProject) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Project project = projRep.findById(project_id).orElseThrow(() -> new ResourceNotFoundException("Project", "id", project_id));
        if ((project.getPerson() == null) || !(project.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The project " + project.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        } else {
            updatedProject.setId(project_id);
            updatedProject.setPerson(person);
            return projRep.save(updatedProject);
        }
    }

    @Override
    public void deleteProject(Long person_id, Long project_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Project project = projRep.findById(project_id).orElseThrow(() -> new ResourceNotFoundException("Project", "id", project_id));
        if ((project.getPerson() == null) || !(project.getPerson()).equals(person)) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The project " + project.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        } else {
            projRep.deleteById(project_id);
        }
    }

    @Override
    public void deleteAllProjectsFromPerson(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        List<Project> projectsList = projRep.findByPersonId(person_id);
        if (projectsList.isEmpty()) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't have associated projects.");
        } else {
            for (Project project : projectsList) {
                Long project_id = project.getId();
                projRep.deleteById(project_id);
            }
        }
    }

}
