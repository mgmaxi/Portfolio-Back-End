package com.example.portfolio.service;

import com.example.portfolio.entity.Language;
import com.example.portfolio.entity.Person;
import com.example.portfolio.repository.LanguageRepository;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService implements ILanguageService {

    @Autowired
    private LanguageRepository lanRep;

    @Autowired
    private PersonRepository perRep;

    @Override
    public List<Language> getLanguages() {
        return lanRep.findAll();
    }

    @Override
    public Language findByLanguageId(Long language_id) {
        return lanRep.findById(language_id).orElseThrow(() -> new ResourceNotFoundException("Language", "id", language_id));
    }

    @Override
    public List<Language> findByPersonId(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        return person.getLanguages();
    }

    @Override
    public Language createLanguage(Language language) {
        return lanRep.save(language);
    }

    @Override
    public Language updateLanguage(Long language_id, Language updatedLanguage) {
        lanRep.findById(language_id).orElseThrow(() -> new ResourceNotFoundException("Language", "id", language_id));
        updatedLanguage.setId(language_id);
        return lanRep.save(updatedLanguage);
    }

    @Override
    public void deleteLanguage(Long language_id) {
        lanRep.findById(language_id).orElseThrow(() -> new ResourceNotFoundException("Language", "id", language_id));
        lanRep.deleteById(language_id);
    }

}
