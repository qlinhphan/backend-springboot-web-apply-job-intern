package vn.BAITAP.TestAPI.controller.forDashBoard;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Job;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.UserJob;
import vn.BAITAP.TestAPI.domain.dto.DashboardDTO;
import vn.BAITAP.TestAPI.service.JobService;
import vn.BAITAP.TestAPI.service.UserJobService;
import vn.BAITAP.TestAPI.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DashboardController {

    private UserService userService;
    private JobService jobService;
    private UserJobService userJobService;

    public DashboardController(UserService userService, JobService jobService, UserJobService userJobService) {
        this.userService = userService;
        this.jobService = jobService;
        this.userJobService = userJobService;
    }

    @GetMapping("/dash-view")
    public ResponseEntity<?> getMethodName() {
        List<User> users = this.userService.findAllUsers();
        long countUsers = users.size();

        // khi them job thi phai thiet lap xem job ay thuoc company nao, ke ca manager
        // va admin
        List<Job> jobs = this.jobService.findAllJobNoPage();
        long countJobs = jobs.size();

        List<UserJob> userJobs = this.userJobService.findAllNoHasPage();
        long countAllListRegister = userJobs.size();

        List<UserJob> listPending = new ArrayList<>();
        for (UserJob uj : userJobs) {
            if (uj.isAllowDel() == true) {
                listPending.add(uj);
            }
        }
        long countPending = listPending.size();

        List<UserJob> listOk = new ArrayList<>();
        for (UserJob uj : userJobs) {
            if (uj.isAllowDel() == false) {
                listOk.add(uj);
            }
        }
        long countOk = listOk.size();

        DashboardDTO dbDTO = new DashboardDTO();
        dbDTO.setCountUser(countUsers);
        dbDTO.setCountJob(countJobs);
        dbDTO.setCountAllInListRegister(countAllListRegister);
        dbDTO.setCountPending(countPending);
        dbDTO.setCountSuccess(countOk);

        return ResponseEntity.ok().body(dbDTO);
    }

}
