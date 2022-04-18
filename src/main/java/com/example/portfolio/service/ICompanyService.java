package com.example.portfolio.service;

import com.example.portfolio.entity.Company;
import java.util.List;

public interface ICompanyService {

    public List<Company> getCompanies();

    public Company findByCompanyId(Long company_id);

    public Company createCompany(Company company);

    public Company updateCompany(Long company_id, Company company);

    public void deleteCompany(Long company_id);

}
