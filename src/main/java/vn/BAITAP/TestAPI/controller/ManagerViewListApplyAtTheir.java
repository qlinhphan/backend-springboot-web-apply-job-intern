package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.domain.JobCompany;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.UserJob;
import vn.BAITAP.TestAPI.service.JobCompanyService;
import vn.BAITAP.TestAPI.service.UserJobService;
import vn.BAITAP.TestAPI.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ManagerViewListApplyAtTheir {

    private JobCompanyService jobCompanyService;
    private UserService userService;
    private UserJobService userJobService;

    public ManagerViewListApplyAtTheir(JobCompanyService jobCompanyService, UserService userService,
            UserJobService userJobService) {
        this.jobCompanyService = jobCompanyService;
        this.userService = userService;
        this.userJobService = userJobService;
    }

    // người mana sẽ xem xem ai apply vào cv mà họ tạo
    @GetMapping("/manager-view-who-applied")
    public ResponseEntity<?> getMethodName() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userService.findUserByEmail(email);

        List<JobCompany> jcs = this.jobCompanyService.findAllByUser(user);
        List<Job> jobOfLogin = new ArrayList<>();
        for (JobCompany cs : jcs) {
            jobOfLogin.add(cs.getJob());
        }
        List<UserJob> userJobs = this.userJobService.findAllNoHasPage();

        List<UserJob> userJobData = new ArrayList<>();
        for (UserJob uj : userJobs) {
            for (Job j : jobOfLogin) {
                if (uj.getJob().equals(j)) {
                    userJobData.add(uj);
                }
            }
        }

        return ResponseEntity.ok().body(userJobData);
    }

}
