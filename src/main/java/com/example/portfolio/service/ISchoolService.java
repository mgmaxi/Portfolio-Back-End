package com.example.portfolio.service;

import com.example.portfolio.entity.School;
import java.util.List;

public interface ISchoolService {

    public List<School> getSchools();

    public School findBySchoolId(Long school_id);

    public School createSchool(School school);

    public School updateSchool(Long school_id, School school);

    public void deleteSchool(Long school_id);

}
