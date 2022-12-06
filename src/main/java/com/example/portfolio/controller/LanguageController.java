package com.example.portfolio.controller;

import com.example.portfolio.entity.Language;
import com.example.portfolio.service.LanguageService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LanguageController {

    @Autowired
    private LanguageService lanServ;

    @GetMapping("/languages")
    @ResponseBody
    public List<Language> getLanguages() {
        return lanServ.getLanguages();
    }

    @GetMapping("/languages/{language_id}")
    public ResponseEntity<Language> findByLanguageId(@PathVariable Long language_id) {
        return new ResponseEntity<>(lanServ.findByLanguageId(language_id), HttpStatus.OK);
    }

    @GetMapping("/languages/persons/{person_id}")
    public List<Language> findByPersonId(@PathVariable Long person_id) {
        return lanServ.findByPersonId(person_id);
    }

    @PostMapping("/languages")
    public ResponseEntity<Language> createLanguage(@Valid @RequestBody Language language) {
        return new ResponseEntity<>(lanServ.createLanguage(language), HttpStatus.CREATED);
    }

    @PutMapping("/languages/{language_id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Long language_id, @Valid @RequestBody Language language) {
        return new ResponseEntity<>(lanServ.updateLanguage(language_id, language), HttpStatus.OK);
    }

    @DeleteMapping("/languages/{language_id}")
    public ResponseEntity<String> deleteLanguage(@PathVariable Long language_id) {
        lanServ.deleteLanguage(language_id);
        return new ResponseEntity<>("The language has been deleted.", HttpStatus.OK);
    }
}
