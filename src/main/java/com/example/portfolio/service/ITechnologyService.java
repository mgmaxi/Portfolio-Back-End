package com.example.portfolio.service;

import com.example.portfolio.entity.Technology;
import java.util.List;

public interface ITechnologyService {

    public List<Technology> getTechnologies();

    public Technology findByTechnologyId(Long technology_id);
    
    public List<Technology> findByPersonId(Long person_id);

    public Technology createTechnology(Technology technology);

    public Technology updateTechnology(Long technology_id, Technology technology);

    public void deleteTechnology(Long technology_id);

}
