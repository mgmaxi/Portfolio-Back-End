package com.example.portfolio.service;

import com.example.portfolio.entity.Education;
import java.util.List;

public interface IEducationService {

    public List<Education> getEducations();

    public Education findByEducationId(Long person_id, Long education_id);

    public List<Education> findByPersonId(Long person_id);

    public Education createEducation(Long person_id, Long school_id, Education education);

    public Education updateEducation(Long person_id, Long school_id, Long education_id, Education education);

    public void deleteEducation(Long person_id, Long education_id);

    public void deleteAllEducationsFromPerson(Long person_id);

}
