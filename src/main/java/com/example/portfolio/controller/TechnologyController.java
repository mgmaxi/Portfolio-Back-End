package com.example.portfolio.controller;

import com.example.portfolio.dto.MessageSent;
import com.example.portfolio.entity.Technology;
import com.example.portfolio.repository.TechnologyRepository;
import com.example.portfolio.service.TechnologyService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api")
public class TechnologyController {

    @Autowired
    private TechnologyService techServ;
    
    @Autowired
    private TechnologyRepository techRep;

    @GetMapping("/technologies")
    public List<Technology> getTechnologies() {
        return techServ.getTechnologies();
    }

    @GetMapping("/technologies/{technology_id}")
    public ResponseEntity<Technology> findByTechnologyId(@PathVariable Long technology_id) {
        return new ResponseEntity<>(techServ.findByTechnologyId(technology_id), HttpStatus.OK);
    }

    @GetMapping("/technologies/persons/{person_id}")
    public List<Technology> findByPersonId(@PathVariable Long person_id) {
        return techServ.findByPersonId(person_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/technologies")
    public ResponseEntity<Technology> createTechnology(@Valid @RequestBody Technology technology) {
         if (techRep.existsByName(technology.getName())) {
            return new ResponseEntity(new MessageSent("There is already a technology with that name. Try another."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(techServ.createTechnology(technology), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/technologies/{technology_id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Long technology_id, @Valid @RequestBody Technology technology) {
        return new ResponseEntity<>(techServ.updateTechnology(technology_id, technology), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/technologies/{technology_id}")
    public ResponseEntity deleteTechnology(@PathVariable Long technology_id) {
        techServ.deleteTechnology(technology_id);
        return new ResponseEntity<>("The technology has been deleted.", HttpStatus.OK);
    }
}
