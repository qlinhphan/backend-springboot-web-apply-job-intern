package vn.BAITAP.TestAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.repository.JobRepository;

@Service
public class JobService {
    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job saveJob(Job job) {
        return this.jobRepository.save(job);
    }

    public Page<Job> findAllJobHasPage(Pageable pageable) {
        return this.jobRepository.findAll(pageable);
    }

    public List<Job> findAllJobNoPage() {
        return this.jobRepository.findAll();
    }

    public Job findJobById(long id) {
        return this.jobRepository.findById(id);
    }

    public void deleteJobById(long id) {
        this.jobRepository.deleteById(id);
    }
}
