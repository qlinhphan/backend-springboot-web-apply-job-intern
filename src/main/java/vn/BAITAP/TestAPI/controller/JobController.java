package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.domain.dto.ObjectPaginate;
import vn.BAITAP.TestAPI.service.JobService;
import vn.BAITAP.TestAPI.service.Except.NotExistUserById;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> postMethodName(@RequestBody Job job) {
        Job jobSave = this.jobService.saveJob(job);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(jobSave);
    }

    @GetMapping("/jobs/haspage")
    public ResponseEntity<?> getMethodName(@RequestParam("current") String current,
            @RequestParam("limit") String limit) {
        int current_int = Integer.parseInt(current);
        int limit_int = Integer.parseInt(limit);
        Pageable pageable = PageRequest.of(current_int - 1, limit_int);
        List<Job> jobsNoPage = this.jobService.findAllJobNoPage();
        Page<Job> jobsPage = this.jobService.findAllJobHasPage(pageable);
        List<Job> jobs = jobsPage.getContent();

        ObjectPaginate op = new ObjectPaginate<>();
        op.setCurrent(current_int);
        op.setLimit(limit_int);
        op.setPages(jobsPage.getTotalPages());
        op.setSumObj(jobsNoPage.size());
        op.setData(jobs);

        return ResponseEntity.ok().body(op);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity getMethodName(@PathVariable("id") long id) {
        Job j = this.jobService.findJobById(id);
        if (j == null) {
            throw new NotExistUserById("Khong ton tai job nay in DB");
        } else {
            return ResponseEntity.ok().body(j);
        }
    }

    @PutMapping("jobs/update")
    public ResponseEntity<?> putMethodName(@RequestBody Job jobPost) {
        Job j = this.jobService.findJobById(jobPost.getId());
        if (j == null) {
            throw new NotExistUserById("Khong ton tai job nay in DB");
        } else {
            if (jobPost.getBenefit() != null) {
                j.setBenefit(jobPost.getBenefit());
            }
            if (jobPost.isActive() == true || jobPost.isActive() == false) {
                j.setActive(jobPost.isActive());
            }
            if (jobPost.getDescription() != null) {
                j.setDescription(jobPost.getDescription());
            }
            if (jobPost.getNameJob() != null) {
                j.setNameJob(jobPost.getNameJob());
            }
            if (jobPost.getJobRequire() != null) {
                j.setJobRequire(jobPost.getJobRequire());
            }
            j.setLimitPeopleForJob(jobPost.getLimitPeopleForJob());

            Job jobUpdate = this.jobService.saveJob(j);
            return ResponseEntity.ok().body(jobUpdate);
        }
    }

    @DeleteMapping("/jobs/del/{id}")
    public ResponseEntity<?> deleteJobss(@PathVariable("id") long id) {
        Job j = this.jobService.findJobById(id);
        if (j == null) {
            throw new NotExistUserById("Khong ton tai job nay in DB de xoa");
        } else {
            this.jobService.deleteJobById(id);
            return ResponseEntity.ok().body("Xoa thanh cong");
        }
    }

}
