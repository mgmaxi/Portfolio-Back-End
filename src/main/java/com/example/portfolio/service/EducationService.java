package com.example.portfolio.service;

import com.example.portfolio.entity.Education;
import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.School;
import com.example.portfolio.repository.EducationRepository;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.repository.SchoolRepository;
import com.example.portfolio.exceptions.PortfolioAppException;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EducationService implements IEducationService {

    @Autowired
    private EducationRepository eduRep;

    @Autowired
    private PersonRepository perRep;

    @Autowired
    private SchoolRepository schRep;

    @Override
    public List<Education> getEducations() {
        return eduRep.findAll();
    }

    @Override
    public Education findByEducationId(Long person_id, Long education_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Education education = eduRep.findById(education_id).orElseThrow(() -> new ResourceNotFoundException("Education", "id", education_id));
        if ((education.getPerson() == null) || !(education.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The education " + education.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        } else {
            return education;
        }
    }

    @Override
    public List<Education> findByPersonId(Long person_id) {
        perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        return eduRep.findByPersonId(person_id);
    }

    @Override
    public Education createEducation(Long person_id, Long school_id, Education education) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        education.setPerson(person);
        School school = schRep.findById(school_id).orElseThrow(() -> new ResourceNotFoundException("Institution", "id", school_id));
        education.setSchool(school);
        return eduRep.save(education);
    }

    @Override
    public Education updateEducation(Long person_id, Long school_id, Long education_id, Education updatedEducation) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        School school = schRep.findById(school_id).orElseThrow(() -> new ResourceNotFoundException("Institution", "id", school_id));
        Education education = eduRep.findById(education_id).orElseThrow(() -> new ResourceNotFoundException("Education", "id", education_id));
        if ((education.getPerson() == null) || !(education.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The education " + education.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        }
        updatedEducation.setId(education_id);
        updatedEducation.setPerson(person);
        updatedEducation.setSchool(school);
        return eduRep.save(updatedEducation);
    }

    @Override
    public void deleteEducation(Long person_id, Long education_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Education education = eduRep.findById(education_id).orElseThrow(() -> new ResourceNotFoundException("Education", "id", education_id));
        if ((education.getPerson() == null) || !(education.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The education " + education.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        } else {
            eduRep.deleteById(education_id);
        }
    }

    @Override
    public void deleteAllEducationsFromPerson(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        List<Education> educationsList = eduRep.findByPersonId(person_id);
        if (educationsList.isEmpty()) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't have associated educations.");
        } else {
            for (Education education : educationsList) {
                Long education_id = education.getId();
                eduRep.deleteById(education_id);
            }
        }
    }
}
