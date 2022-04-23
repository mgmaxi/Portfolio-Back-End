package com.example.portfolio.controller;

import com.example.portfolio.entity.Education;
import com.example.portfolio.service.EducationService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api")
public class EducationController {

    @Autowired
    private EducationService eduServ;

    @GetMapping("/educations")
    public List<Education> getEducations() {
        return eduServ.getEducations();
    }

    @GetMapping("/educations/{education_id}/persons/{person_id}")
    public ResponseEntity<Education> findByEducationId(@PathVariable Long person_id, @PathVariable Long education_id) {
        return new ResponseEntity<>(eduServ.findByEducationId(person_id, education_id), HttpStatus.OK);
    }

    @GetMapping("/educations/persons/{person_id}")
    public List<Education> findByPersonId(@PathVariable Long person_id) {
        return eduServ.findByPersonId(person_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/educations/persons/{person_id}/schools/{school_id}")
    public ResponseEntity<Education> createEducation(@PathVariable Long person_id, @PathVariable Long school_id, @Valid @RequestBody Education education) {
        return new ResponseEntity<>(eduServ.createEducation(person_id, school_id, education), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/educations/{education_id}/persons/{person_id}/schools/{school_id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long person_id, @PathVariable Long school_id, @PathVariable Long education_id, @Valid @RequestBody Education education) {
        return new ResponseEntity<>(eduServ.updateEducation(person_id, school_id, education_id, education), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educations/{education_id}/persons/{person_id}")
    public ResponseEntity<String> deleteEducation(@PathVariable Long person_id, @PathVariable Long education_id) {
        eduServ.deleteEducation(person_id, education_id);
        return new ResponseEntity<>("The education has been deleted.", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/educations/persons/{person_id}")
    public ResponseEntity<String> deleteAllEducationsFromPerson(@PathVariable Long person_id) {
        eduServ.deleteAllEducationsFromPerson(person_id);
        return new ResponseEntity<>("All educations of the person have been deleted.", HttpStatus.OK);
    }
}
