package vn.BAITAP.TestAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.Company;
import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.domain.JobCompany;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.repository.JobCompanyRepository;

@Service
public class JobCompanyService {
    private JobCompanyRepository jobCompanyRepository;

    public JobCompanyService(JobCompanyRepository jobCompanyRepository) {
        this.jobCompanyRepository = jobCompanyRepository;
    }

    public JobCompany createJobCom(JobCompany jc) {
        return this.jobCompanyRepository.save(jc);
    }

    public Page<JobCompany> findAllJobComps(Pageable p) {
        return this.jobCompanyRepository.findAll(p);
    }

    public List<JobCompany> findAllNoHasPage() {
        return this.jobCompanyRepository.findAll();
    }

    public List<JobCompany> findAllByUser(User user) {
        return this.jobCompanyRepository.findByUser(user);
    }

    public JobCompany findJCById(long id) {
        return this.jobCompanyRepository.findById(id);
    }

    public JobCompany findJCByJob(Job j) {
        return this.jobCompanyRepository.findByJob(j);
    }

    public void deleteJCByJob(Job j) {
        this.jobCompanyRepository.deleteByJob(j);
    }

    public List<JobCompany> findJCByCom(Company p) {
        return this.jobCompanyRepository.findByCompany(p);
    }
}
