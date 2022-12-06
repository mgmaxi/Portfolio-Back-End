package com.example.portfolio.controller;

import com.example.portfolio.dto.MessageSent;
import com.example.portfolio.entity.School;
import com.example.portfolio.repository.SchoolRepository;
import com.example.portfolio.service.SchoolService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private SchoolService schServ;
    
    @Autowired
    private SchoolRepository schRep;

    @GetMapping("/schools")
    @ResponseBody
    public List<School> getSchools() {
        return schServ.getSchools();
    }

    @GetMapping("/schools/{school_id}")
    public ResponseEntity<School> findBySchoolId(@PathVariable Long school_id) {
        return new ResponseEntity<>(schServ.findBySchoolId(school_id), HttpStatus.OK);
    }

    @PostMapping("/schools")
    public ResponseEntity<School> createSchool(@Valid @RequestBody School school) {
        if (schRep.existsByName(school.getName())) {
            return new ResponseEntity(new MessageSent("There is already an academic institution with that name. Try another."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(schServ.createSchool(school), HttpStatus.CREATED);
    }

    @PutMapping("/schools/{school_id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long school_id, @Valid @RequestBody School school) {
        return new ResponseEntity<>(schServ.updateSchool(school_id, school), HttpStatus.OK);
    }

    @DeleteMapping("/schools/{school_id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long school_id) {
        schServ.deleteSchool(school_id);
        return new ResponseEntity<>("The academic institution has been deleted.", HttpStatus.OK);
    }
}
