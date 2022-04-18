package com.example.portfolio.service;

import com.example.portfolio.entity.Experience;
import java.util.List;

public interface IExperienceService {

    public List<Experience> getExperiences();

    public Experience findByExperienceId(Long person_id, Long experience_id);

    public List<Experience> findByPersonId(Long person_id);

    public Experience createExperience(Long person_id, Long company_id, Experience experience);

    public Experience updateExperience(Long person_id, Long company_id, Long experience_id, Experience experience);

    public void deleteExperience(Long person_id, Long experience_id);
    
    public void deleteAllExperiencesFromPerson(Long person_id);

}
