package com.example.portfolio.service;

import com.example.portfolio.entity.Person;
import com.example.portfolio.entity.Technology;
import com.example.portfolio.repository.TechnologyRepository;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import com.example.portfolio.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService implements ITechnologyService {

    @Autowired
    private TechnologyRepository techRep;

    @Autowired
    private PersonRepository perRep;

    @Override
    public List<Technology> getTechnologies() {
        return techRep.findAll();
    }

    @Override
    public Technology findByTechnologyId(Long technology_id) {
        return techRep.findById(technology_id).orElseThrow(() -> new ResourceNotFoundException("Technology", "id", technology_id));
    }

    @Override
    public List<Technology> findByPersonId(Long person_id) {
        Person person = perRep.findById(person_id).orElseThrow(() -> new ResourceNotFoundException("Person", "id", person_id));
        return person.getTechnologies();
    }

    @Override
    public Technology createTechnology(Technology technology) {
        return techRep.save(technology);
    }

    @Override
    public Technology updateTechnology(Long technology_id, Technology updatedTechnology) {
        techRep.findById(technology_id).orElseThrow(() -> new ResourceNotFoundException("Technology", "id", technology_id));
        updatedTechnology.setId(technology_id);
        return techRep.save(updatedTechnology);
    }

    @Override
    public void deleteTechnology(Long technology_id) {
        techRep.findById(technology_id).orElseThrow(() -> new ResourceNotFoundException("Technology", "id", technology_id));
        techRep.deleteById(technology_id);
    }

}
