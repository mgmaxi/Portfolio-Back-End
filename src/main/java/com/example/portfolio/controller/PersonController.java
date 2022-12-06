package com.example.portfolio.controller;

import com.example.portfolio.dto.PersonDTO;
import com.example.portfolio.entity.Person;
import com.example.portfolio.service.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService perServ;

    @GetMapping("/persons")
    @ResponseBody
    public List<Person> getPersons() {
        return perServ.getPersons();
    }

    @GetMapping("/persons/{person_id}")
    public ResponseEntity<Person> findByPersonId(@PathVariable Long person_id) {
        return new ResponseEntity<>(perServ.findByPersonId(person_id), HttpStatus.OK);
    }

    @GetMapping("/persons/{person_id}/profile")
    public ResponseEntity<PersonDTO> findPersonDTOByPersonId(@PathVariable Long person_id) {
        return new ResponseEntity<>(perServ.findPersonDTOByPersonId(person_id), HttpStatus.OK);
    }

    @PostMapping("/persons/users/{account_id}")
    public ResponseEntity<Person> createPerson(@PathVariable Long user_id, @Valid @RequestBody Person person) {
        return new ResponseEntity<>(perServ.createPerson(user_id, person), HttpStatus.CREATED);
    }

    @PutMapping("/persons/{person_id}/users/{user_id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long user_id, @PathVariable Long person_id, @Valid @RequestBody Person person) {
        return new ResponseEntity<>(perServ.updatePerson(user_id, person_id, person), HttpStatus.OK);
    }

    @DeleteMapping("/persons/{person_id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long person_id) {
        perServ.deletePerson(person_id);
        return new ResponseEntity<>("The person has been deleted.", HttpStatus.OK);
    }

    // Add technology to person
    @PutMapping("/persons/{person_id}/technologies/{technology_id}")
    public ResponseEntity<Person> addTechnologyToPerson(@PathVariable Long person_id, @PathVariable Long technology_id) {
        return new ResponseEntity<>(perServ.addTechnologyToPerson(person_id, technology_id), HttpStatus.OK);
    }

    // Delete technology of person
    @DeleteMapping("/persons/{person_id}/technologies/{technology_id}")
    public ResponseEntity<String> deleteTechnologyOfPerson(@PathVariable Long person_id, @PathVariable Long technology_id) {
        perServ.deleteTechnologyOfPerson(person_id, technology_id);
        return new ResponseEntity<>("The technology has been deleted from the person.", HttpStatus.OK);
    }

    // Delete ALL technologies from person
    @DeleteMapping("/persons/{person_id}/technologies")
    public ResponseEntity<String> deleteAllTechnologiesFromPerson(@PathVariable Long person_id) {
        perServ.deleteAllTechnologiesFromPerson(person_id);
        return new ResponseEntity<>("All technologies of the person have been deleted.", HttpStatus.OK);
    }

    // Add language to person
    @PutMapping("/persons/{person_id}/languages/{language_id}")
    public ResponseEntity<Person> addLanguageToPerson(@PathVariable Long person_id, @PathVariable Long language_id) {
        return new ResponseEntity<>(perServ.addLanguageToPerson(person_id, language_id), HttpStatus.OK);
    }

    // Delete language of person
    @DeleteMapping("/persons/{person_id}/languages/{language_id}")
    public ResponseEntity<String> deleteLanguageOfPerson(@PathVariable Long person_id, @PathVariable Long language_id) {
        perServ.deleteLanguageOfPerson(person_id, language_id);
        return new ResponseEntity<>("The language has been deleted from the person.", HttpStatus.OK);
    }

    // Delete ALL languages of person
    @DeleteMapping("/persons/{person_id}/languages")
    public ResponseEntity<String> deleteAllLanguagesFromPerson(@PathVariable Long person_id) {
        perServ.deleteAllLanguagesFromPerson(person_id);
        return new ResponseEntity<>("All languages of the person have been deleted.", HttpStatus.OK);
    }

}
