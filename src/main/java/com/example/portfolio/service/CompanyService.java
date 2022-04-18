package com.example.portfolio.service;

import com.example.portfolio.entity.Company;
import com.example.portfolio.repository.CompanyRepository;
import com.example.portfolio.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository compRep;

    @Override
    public List<Company> getCompanies() {
        return compRep.findAll();
    }

    @Override
    public Company findByCompanyId(Long company_id) {
        return compRep.findById(company_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", company_id));
    }

    @Override
    public Company createCompany(Company company) {
        return compRep.save(company);
    }

    @Override
    public Company updateCompany(Long company_id, Company updatedCompany) {
        compRep.findById(company_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", company_id));
        updatedCompany.setId(company_id);
        return compRep.save(updatedCompany);
    }

    @Override
    public void deleteCompany(Long company_id) {
        compRep.findById(company_id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", company_id));
        compRep.deleteById(company_id);
    }
}
