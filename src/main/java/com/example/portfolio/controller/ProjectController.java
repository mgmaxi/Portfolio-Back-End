package com.example.portfolio.controller;

import com.example.portfolio.entity.Project;
import com.example.portfolio.service.ProjectService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projServ;

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projServ.getProjects();
    }

    @GetMapping("/projects/{project_id}/persons/{person_id}")
    public ResponseEntity<Project> findByProjectId(@PathVariable Long project_id, @PathVariable Long person_id) {
        return new ResponseEntity<>(projServ.findByProjectId(project_id, person_id), HttpStatus.OK);
    }

    @GetMapping("/projects/persons/{person_id}")
    public List<Project> findByPersonId(@PathVariable Long person_id) {
        return projServ.findByPersonId(person_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/projects/persons/{person_id}")
    public ResponseEntity<Project> createProject(@PathVariable Long person_id, @Valid @RequestBody Project project) {
        return new ResponseEntity<>(projServ.createProject(person_id, project), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/projects/{project_id}/persons/{person_id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long person_id, @PathVariable Long project_id, @Valid @RequestBody Project updatedProject) {
        return new ResponseEntity<>(projServ.updateProject(person_id, project_id, updatedProject), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/projects/{project_id}/persons/{person_id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long person_id, @PathVariable Long project_id) {
        projServ.deleteProject(person_id, project_id);
        return new ResponseEntity<>("The project has been deleted.", HttpStatus.OK);
    }

    // Delete ALL projects from person
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/projects/persons/{person_id}")
    public ResponseEntity<String> deleteAllProjectsFromPerson(@PathVariable Long person_id) {
        projServ.deleteAllProjectsFromPerson(person_id);
        return new ResponseEntity<>("All projects of the person have been deleted", HttpStatus.OK);
    }
}
