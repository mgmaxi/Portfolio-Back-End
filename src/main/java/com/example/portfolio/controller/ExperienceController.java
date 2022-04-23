package com.example.portfolio.controller;

import com.example.portfolio.entity.Experience;
import com.example.portfolio.service.ExperienceService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api")
public class ExperienceController {

    @Autowired
    private ExperienceService expServ;

    @GetMapping("/experiences")
    public List<Experience> getExperiences() {
        return expServ.getExperiences();
    }

    @GetMapping("/experiences/{experience_id}/persons/{person_id}")
    public ResponseEntity<Experience> findByExperienceId(@PathVariable Long experience_id, @PathVariable Long person_id) {
        return new ResponseEntity<>(expServ.findByExperienceId(experience_id, person_id), HttpStatus.OK);
    }

    @GetMapping("/experiences/persons/{person_id}")
    public List<Experience> findByPersonId(@PathVariable Long person_id) {
        return expServ.findByPersonId(person_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/experiences/persons/{person_id}/companies/{company_id}")
    public ResponseEntity<Experience> createExperience(@PathVariable Long person_id, @PathVariable Long company_id, @Valid @RequestBody Experience experience) {
        return new ResponseEntity<>(expServ.createExperience(person_id, company_id, experience), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experiences/{experience_id}/persons/{person_id}/companies/{company_id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long person_id, @PathVariable Long company_id, @PathVariable Long experience_id, @Valid @RequestBody Experience experience) {
        return new ResponseEntity<>(expServ.updateExperience(person_id, company_id, experience_id, experience), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experiences/{experience_id}/persons/{person_id}")
    public ResponseEntity<String> deleteExperience(@PathVariable Long person_id, @PathVariable Long experience_id) {
        expServ.deleteExperience(person_id, experience_id);
        return new ResponseEntity<>("The experience has been deleted.", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experiences/persons/{person_id}")
    public ResponseEntity<String> deleteAllExperiencesFromPerson(@PathVariable Long person_id) {
        expServ.deleteAllExperiencesFromPerson(person_id);
        return new ResponseEntity<>("All experiences of the person have been deleted.", HttpStatus.OK);
    }
}
