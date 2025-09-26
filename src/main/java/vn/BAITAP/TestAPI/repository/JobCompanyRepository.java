package vn.BAITAP.TestAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.domain.JobCompany;
import vn.BAITAP.TestAPI.domain.User;

public interface JobCompanyRepository extends JpaRepository<JobCompany, Long> {
    public JobCompany save(JobCompany j);

    public Page<JobCompany> findAll(Pageable pageable);

    public List<JobCompany> findAll();

    public List<JobCompany> findByUser(User user);

    public JobCompany findById(long id);

    public JobCompany findByJob(Job j);

    public void deleteByJob(Job j);
}
