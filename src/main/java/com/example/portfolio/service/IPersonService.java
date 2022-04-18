package com.example.portfolio.service;

import com.example.portfolio.dto.PersonDTO;
import com.example.portfolio.entity.Person;
import java.util.List;

public interface IPersonService {

    public List<Person> getPersons();

    public Person findByPersonId(Long person_id);

    public PersonDTO findPersonDTOByPersonId(Long person_id);

    public Person createPerson(Long user_id, Person person);

    public Person updatePerson(Long user_id, Long person_id, Person person);

    public void deletePerson(Long person_id);

    public Person addTechnologyToPerson(Long person_id, Long technology_id);

    public void deleteTechnologyOfPerson(Long person_id, Long technology_id);

    public void deleteAllTechnologiesFromPerson(Long person_id);

    public Person addLanguageToPerson(Long person_id, Long language_id);

    public void deleteLanguageOfPerson(Long person_id, Long language_id);

    public void deleteAllLanguagesFromPerson(Long person_id);

}
