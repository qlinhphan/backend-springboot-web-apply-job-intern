package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.domain.JobCompany;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.UserJob;
import vn.BAITAP.TestAPI.domain.dto.ObjectPaginate;
import vn.BAITAP.TestAPI.service.JobCompanyService;
import vn.BAITAP.TestAPI.service.JobService;
import vn.BAITAP.TestAPI.service.UserJobService;
import vn.BAITAP.TestAPI.service.UserService;
import vn.BAITAP.TestAPI.service.Except.NotExistUserById;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserJobController {

    private JobCompanyService jobCompanyService;
    private JobService jobService;
    private UserService userService;
    private UserJobService userJobService;

    public UserJobController(JobCompanyService jobCompanyService, JobService jobService, UserService userService,
            UserJobService userJobService) {
        this.jobCompanyService = jobCompanyService;
        this.jobService = jobService;
        this.userService = userService;
        this.userJobService = userJobService;
    }

    // dieu kien la cai idJob nay phai duoc manager nao do dang ky
    // cung 1 nguoi dung ko the dang ky chung job
    @PostMapping("/user-register-job")
    public ResponseEntity<?> postMethodName(@RequestParam("idJob") String idJob) {
        List<JobCompany> jCompanies = this.jobCompanyService.findAllNoHasPage();
        boolean checkCondition = jCompanies.stream().anyMatch(x -> x.getJob().getId() == Long.parseLong(idJob));
        if (!checkCondition) {
            throw new NotExistUserById("Chua co manager nao them cv nay");
        }
        Job job = this.jobService.findJobById(Long.parseLong(idJob));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userService.findUserByEmail(email);
        List<UserJob> userJobs = this.userJobService.findAllNoHasPage();
        boolean checkUserJobs = userJobs.stream().anyMatch(x -> x.getJob().equals(job) && x.getUser().equals(user));
        if (checkUserJobs) {
            throw new NotExistUserById("Ban da dang ky job nay roi");
        }
        UserJob userJob = new UserJob();
        userJob.setJob(job);
        userJob.setUser(user);
        UserJob ujToSave = this.userJobService.saveUserJob(userJob);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(ujToSave);
    }

    @GetMapping("/user-view-list-registered")
    public ResponseEntity<?> getMethodName(@RequestParam("current") int current, @RequestParam("limit") int limit) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userService.findUserByEmail(email);
        Pageable pageable = PageRequest.of(current - 1, limit);

        Page<UserJob> pUserJob = this.userJobService.findByUserAndPagination(pageable, user);
        List<UserJob> lUserJob = pUserJob.getContent();
        List<UserJob> userJobNoPage = this.userJobService.findAllByUserNoPagi(user);

        ObjectPaginate<Object> op = new ObjectPaginate<>();
        op.setCurrent(current);
        op.setLimit(limit);
        op.setPages(pUserJob.getTotalPages());
        op.setSumObj(userJobNoPage.size());
        op.setData(lUserJob);
        return ResponseEntity.ok().body(op);
    }

}
