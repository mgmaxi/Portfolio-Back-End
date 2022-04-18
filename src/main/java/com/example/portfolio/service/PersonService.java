package com.example.portfolio.service;

import com.example.portfolio.dto.PersonDTO;

import com.example.portfolio.entity.UserPhotos;
import com.example.portfolio.entity.Language;
import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.Technology;
import com.example.portfolio.repository.LanguageRepository;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.repository.TechnologyRepository;
import com.example.portfolio.exceptions.PortfolioAppException;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.portfolio.repository.UserPhotosRepository;
import com.example.portfolio.security.entity.User;
import com.example.portfolio.security.repository.UserRepository;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository perRep;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private TechnologyRepository techRep;

    @Autowired
    private LanguageRepository lanRep;

    @Autowired
    private UserPhotosRepository userPhRep;

    @Override
    public List<Person> getPersons() {
        return perRep.findAll();
    }

    @Override
    public Person findByPersonId(Long person_id) {
        return perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
    }

    @Override
    public PersonDTO findPersonDTOByPersonId(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        UserPhotos userPhotos = person.getUser().getUser_photos();

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId(person_id);
        personDTO.setFirst_name(person.getFirst_name());
        personDTO.setLast_name(person.getLast_name());
        personDTO.setNationality(person.getNationality());
        personDTO.setProfession(person.getProfession());
        personDTO.setAbout(person.getAbout());
        personDTO.setUserphotos_id(userPhotos.getId());
        personDTO.setProfile_photo(userPhotos.getProfile_photo());
        personDTO.setCover_photo(userPhotos.getCover_photo());

        return personDTO;
    }

    @Override
    public Person createPerson(Long user_id, Person person) {
        User user = userRep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));

        if (user.getPerson() == null) {
            user.setPerson(person);
            return perRep.save(person);
        } else {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The user " + user.getUsername() + " already has " + user.getPerson().getFirst_name()+ " as an associated person.");
        }
    }

    @Override
    public Person updatePerson(Long user_id, Long person_id, Person updatedPerson) {
        User user = userRep.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        if ((user.getPerson() == null) || !(user.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't belong to the account " + user.getUsername() + ".");
        } else {
            updatedPerson.setId(person_id);
            updatedPerson.setEducations(person.getEducations());
            updatedPerson.setExperiences(person.getExperiences());
            updatedPerson.setLanguages(person.getLanguages());
            updatedPerson.setTechnologies(person.getTechnologies());
            updatedPerson.setProjects(person.getProjects());
            user.setPerson(updatedPerson);
            return perRep.save(updatedPerson);
        }
    }

    @Override
    public void deletePerson(Long person_id) {
        perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        perRep.deleteById(person_id);
    }

    // Person - Technology
    @Override
    public Person addTechnologyToPerson(Long person_id, Long technology_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Technology technology = techRep.findById(technology_id).orElseThrow(() -> new ResourceNotFoundException("Technology", "id", technology_id));
        List<Technology> knowsTech = person.getTechnologies();
        for (Technology tech : knowsTech) {
            if (tech.getId().equals(technology.getId())) {
                throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " already has the " + technology.getName() + " technology added.");
            }
        }
        person.personKnowsTechnology(technology);
        return perRep.save(person);
    }

    // Person - Technology
    @Override
    public void deleteTechnologyOfPerson(Long person_id, Long technology_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Technology technology = techRep.findById(technology_id).orElseThrow(() -> new ResourceNotFoundException("Technology", "id", technology_id));
        List<Technology> knowsTech = person.getTechnologies();
        for (Technology tech : knowsTech) {
            if (tech.getId().equals(technology.getId())) {
                knowsTech.remove(technology);
                person.setTechnologies(knowsTech);
                perRep.save(person);
                return;
            }
        }
        throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't have the " + technology.getName() + " technology added.");
    }

    // Person - Technology
    @Override
    public void deleteAllTechnologiesFromPerson(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Persona", "id", person_id));
        List<Technology> technologiesList = person.getTechnologies();
        if (technologiesList.isEmpty()) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't have associated technologies.");
        }
        person.setTechnologies(null);
        perRep.save(person);
    }

    // Person - Language
    @Override
    public Person addLanguageToPerson(Long person_id, Long language_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Language language = lanRep.findById(language_id).orElseThrow(() -> new ResourceNotFoundException("Language", "id", language_id));
        List<Language> knowsLan = person.getLanguages();
        for (Language lan : knowsLan) {
            if (lan.getId().equals(language.getId())) {
                throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " already has the " + language.getName() + " language added.");
            }
        }
        person.personKnowsLanguage(language);
        return perRep.save(person);
    }

    // Person - Language
    @Override
    public void deleteLanguageOfPerson(Long person_id, Long language_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Language language = lanRep.findById(language_id).orElseThrow(() -> new ResourceNotFoundException("Language", "id", language_id));
        List<Language> knowsLan = person.getLanguages();
        for (Language lan : knowsLan) {
            if (lan.getId().equals(language.getId())) {
                knowsLan.remove(language);
                person.setLanguages(knowsLan);
                perRep.save(person);
                return;
            }
        }
        throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't have the " + language.getName() + " language added.");
    }

    // Person - Language
    @Override
    public void deleteAllLanguagesFromPerson(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        List<Language> languagesList = person.getLanguages();
        if (languagesList.isEmpty()) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't have associated languages.");
        }
        person.setLanguages(null);
        perRep.save(person);
    }

}
