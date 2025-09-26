package vn.BAITAP.TestAPI.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

public class JobCompanyDTO {
    private String nameJob;
    private String descriptionJob;
    private boolean activeJob;
    private String requireJob;
    private String benefitJob;
    private long limitPeopleForJob;

    private String nameComp;
    private String addressComp;
    private String leaderComp;
    private String sizeComp;

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getDescriptionJob() {
        return descriptionJob;
    }

    public void setDescriptionJob(String descriptionJob) {
        this.descriptionJob = descriptionJob;
    }

    public boolean isActiveJob() {
        return activeJob;
    }

    public void setActiveJob(boolean activeJob) {
        this.activeJob = activeJob;
    }

    public String getBenefitJob() {
        return benefitJob;
    }

    public void setBenefitJob(String benefitJob) {
        this.benefitJob = benefitJob;
    }

    public String getNameComp() {
        return nameComp;
    }

    public void setNameComp(String nameComp) {
        this.nameComp = nameComp;
    }

    public String getAddressComp() {
        return addressComp;
    }

    public void setAddressComp(String addressComp) {
        this.addressComp = addressComp;
    }

    public String getLeaderComp() {
        return leaderComp;
    }

    public void setLeaderComp(String leaderComp) {
        this.leaderComp = leaderComp;
    }

    public String getSizeComp() {
        return sizeComp;
    }

    public void setSizeComp(String sizeComp) {
        this.sizeComp = sizeComp;
    }

    public long getLimitPeopleForJob() {
        return limitPeopleForJob;
    }

    public void setLimitPeopleForJob(long limitPeopleForJob) {
        this.limitPeopleForJob = limitPeopleForJob;
    }

    public String getRequireJob() {
        return requireJob;
    }

    public void setRequireJob(String requireJob) {
        this.requireJob = requireJob;
    }

}
