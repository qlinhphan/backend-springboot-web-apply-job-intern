package vn.BAITAP.TestAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.BAITAP.TestAPI.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    public Job save(Job job);

    public Page<Job> findAll(Pageable pageable);

    public List<Job> findAll();

    public Job findById(long id);

    public void deleteById(long id);
}
