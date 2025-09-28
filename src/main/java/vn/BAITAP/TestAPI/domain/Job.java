package vn.BAITAP.TestAPI.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String nameJob;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    private boolean active;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String jobRequire;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String benefit;

    private long limitPeopleForJob; // gioi han nguoi dang ky

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<UserJob> usersJobs;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<JobCompany> jobCompanies;

    private String typeJob;
    private long salary;

    public String getTypeJob() {
        return typeJob;
    }

    public void setTypeJob(String typeJob) {
        this.typeJob = typeJob;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public List<UserJob> getUsersJobs() {
        return usersJobs;
    }

    public void setUsersJobs(List<UserJob> usersJobs) {
        this.usersJobs = usersJobs;
    }

    public String getJobRequire() {
        return jobRequire;
    }

    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }

    @PrePersist
    public void defaultOfActive() {
        this.active = true;
    }

    public List<JobCompany> getJobCompanies() {
        return jobCompanies;
    }

    public void setJobCompanies(List<JobCompany> jobCompanies) {
        this.jobCompanies = jobCompanies;
    }

    public long getLimitPeopleForJob() {
        return limitPeopleForJob;
    }

    public void setLimitPeopleForJob(long limitPeopleForJob) {
        this.limitPeopleForJob = limitPeopleForJob;
    }

}
