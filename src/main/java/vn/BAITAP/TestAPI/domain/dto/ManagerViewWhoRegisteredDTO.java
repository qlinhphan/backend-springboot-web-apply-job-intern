package vn.BAITAP.TestAPI.domain.dto;

public class ManagerViewWhoRegisteredDTO {
    private String idJob;
    private String nameJob;
    private String yourEmail;
    private String EmailOfWhoRegistered;

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getYourEmail() {
        return yourEmail;
    }

    public void setYourEmail(String yourEmail) {
        this.yourEmail = yourEmail;
    }

    public String getEmailOfWhoRegistered() {
        return EmailOfWhoRegistered;
    }

    public void setEmailOfWhoRegistered(String emailOfWhoRegistered) {
        EmailOfWhoRegistered = emailOfWhoRegistered;
    }

}
