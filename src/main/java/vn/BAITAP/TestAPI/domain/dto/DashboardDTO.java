package vn.BAITAP.TestAPI.domain.dto;

import java.util.List;

import vn.BAITAP.TestAPI.domain.UserJob;

public class DashboardDTO {
    private long countUser;
    private long countJob;
    private long countAllInListRegister;
    private long countPending;
    private long countSuccess;
    private List<UserJob> userJobs;

    public long getCountUser() {
        return countUser;
    }

    public void setCountUser(long countUser) {
        this.countUser = countUser;
    }

    public long getCountJob() {
        return countJob;
    }

    public void setCountJob(long countJob) {
        this.countJob = countJob;
    }

    public long getCountAllInListRegister() {
        return countAllInListRegister;
    }

    public void setCountAllInListRegister(long countAllInListRegister) {
        this.countAllInListRegister = countAllInListRegister;
    }

    public long getCountPending() {
        return countPending;
    }

    public void setCountPending(long countPending) {
        this.countPending = countPending;
    }

    public long getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(long countSuccess) {
        this.countSuccess = countSuccess;
    }

    public List<UserJob> getUserJobs() {
        return userJobs;
    }

    public void setUserJobs(List<UserJob> userJobs) {
        this.userJobs = userJobs;
    }

}
