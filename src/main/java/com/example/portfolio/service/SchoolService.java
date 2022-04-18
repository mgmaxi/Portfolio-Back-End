package com.example.portfolio.service;

import com.example.portfolio.entity.School;
import com.example.portfolio.repository.SchoolRepository;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService implements ISchoolService {

    @Autowired
    private SchoolRepository schRep;

    @Override
    public List<School> getSchools() {
        return schRep.findAll();
    }

    @Override
    public School findBySchoolId(Long school_id) {
        return schRep.findById(school_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", school_id));
    }

    @Override
    public School createSchool(School school) {
        return schRep.save(school);
    }

    @Override
    public School updateSchool(Long school_id, School updatedSchool) {
        schRep.findById(school_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", school_id));
        updatedSchool.setId(school_id);
        return schRep.save(updatedSchool);
    }

    @Override
    public void deleteSchool(Long school_id) {
        schRep.findById(school_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", school_id));
        schRep.deleteById(school_id);
    }
}
