package vn.BAITAP.TestAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.BAITAP.TestAPI.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    public Job save(Job job);

    public Page<Job> findAll(Pageable pageable);

    public List<Job> findAll();

    public Job findById(long id);

    public void deleteById(long id);

    public List<Job> findAll(Specification<Job> spec);
}
