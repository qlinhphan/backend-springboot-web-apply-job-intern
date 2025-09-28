package vn.BAITAP.TestAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.Company;
import vn.BAITAP.TestAPI.repository.CompanyRepository;
import vn.BAITAP.TestAPI.service.Spec.CompanySpecification;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private CompanySpecification companySpecification;

    public CompanyService(CompanyRepository companyRepository, CompanySpecification companySpecification) {
        this.companyRepository = companyRepository;
        this.companySpecification = companySpecification;
    }

    public Company saveCompany(Company com) {
        return this.companyRepository.save(com);
    }

    public Page<Company> findAllHasPage(Pageable p) {
        return this.companyRepository.findAll(p);
    }

    public List<Company> findAllCompanies() {
        return this.companyRepository.findAll();
    }

    public Company findCompById(long id) {
        return this.companyRepository.findById(id);
    }

    public void deleteCompanyById(long id) {
        this.companyRepository.deleteById(id);
    }

    public List<Company> findAllSpeci(Specification<Company> spe) {
        return this.companyRepository.findAll(spe);
    }
}
