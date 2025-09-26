package vn.BAITAP.TestAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.Company;
import vn.BAITAP.TestAPI.repository.CompanyRepository;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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
}
