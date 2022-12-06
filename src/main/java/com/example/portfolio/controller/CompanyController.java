package com.example.portfolio.controller;

import com.example.portfolio.dto.MessageSent;
import com.example.portfolio.entity.Company;
import com.example.portfolio.repository.CompanyRepository;
import com.example.portfolio.service.CompanyService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService compServ;
    
    @Autowired
    private CompanyRepository compRep;

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return compServ.getCompanies();
    }

    @GetMapping("/companies/{company_id}")
    public ResponseEntity<Company> findByCompanyId(@PathVariable Long company_id) {
        return new ResponseEntity<>(compServ.findByCompanyId(company_id), HttpStatus.OK);
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
         if (compRep.existsByName(company.getName())) {
            return new ResponseEntity(new MessageSent("There is already a company with that name. Try another."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(compServ.createCompany(company), HttpStatus.CREATED);
    }

    @PutMapping("/companies/{company_id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long company_id, @Valid @RequestBody Company company) {
        return new ResponseEntity<>(compServ.updateCompany(company_id, company), HttpStatus.OK);
    }

    @DeleteMapping("/companies/{company_id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long company_id) {
        compServ.deleteCompany(company_id);
        return new ResponseEntity<>("The company has been deleted.", HttpStatus.OK);
    }
}
