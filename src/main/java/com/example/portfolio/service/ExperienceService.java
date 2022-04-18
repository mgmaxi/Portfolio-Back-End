package com.example.portfolio.service;

import com.example.portfolio.entity.Company;
import com.example.portfolio.entity.Experience;
import com.example.portfolio.entity.Person;
import com.example.portfolio.repository.CompanyRepository;
import com.example.portfolio.repository.ExperienceRepository;
import com.example.portfolio.repository.PersonRepository;
import com.example.portfolio.exceptions.PortfolioAppException;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService implements IExperienceService {

    @Autowired
    private ExperienceRepository expRep;

    @Autowired
    private PersonRepository perRep;

    @Autowired
    private CompanyRepository compRep;

    @Override
    public List<Experience> getExperiences() {
        return expRep.findAll();
    }

    @Override
    public Experience findByExperienceId(Long person_id, Long experience_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Experience experience = expRep.findById(experience_id).orElseThrow(() -> new ResourceNotFoundException("Experience", "id", experience_id));
        if ((experience.getPerson() == null) || !(experience.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The experience " + experience.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        } else {
            return experience;
        }
    }

    @Override
    public List<Experience> findByPersonId(Long person_id) {
        perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        return expRep.findByPersonId(person_id);
    }

    @Override
    public Experience createExperience(Long person_id, Long company_id, Experience experience) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        experience.setPerson(person);
        Company company = compRep.findById(company_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", company_id));
        experience.setCompany(company);
        return expRep.save(experience);
    }

    @Override
    public Experience updateExperience(Long person_id, Long company_id, Long experience_id, Experience updatedExperience) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Company company = compRep.findById(company_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", company_id));
        Experience experience = expRep.findById(experience_id).orElseThrow(() -> new ResourceNotFoundException("Experience", "id", experience_id));
        if ((experience.getPerson() == null) || !(experience.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The experience " + experience.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        }
        updatedExperience.setId(experience_id);
        updatedExperience.setPerson(person);
        updatedExperience.setCompany(company);
        return expRep.save(updatedExperience);
    }

    @Override
    public void deleteExperience(Long person_id, Long experience_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        Experience experience = expRep.findById(experience_id).orElseThrow(() -> new ResourceNotFoundException("Experience", "id", experience_id));
        if ((experience.getPerson() == null) || !(experience.getPerson().equals(person))) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The experience " + experience.getName() + " doesn't belong to " + person.getFirst_name()+ ".");
        } else {
            expRep.deleteById(experience_id);
        }
    }

    @Override
    public void deleteAllExperiencesFromPerson(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        List<Experience> experiencesList = expRep.findByPersonId(person_id);
        if (experiencesList.isEmpty()) {
            throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "The person " + person.getFirst_name()+ " doesn't have associated experiences.");
        } else {
            for (Experience experience : experiencesList) {
                Long experience_id = experience.getId();
                expRep.deleteById(experience_id);
            }
        }
    }

}
