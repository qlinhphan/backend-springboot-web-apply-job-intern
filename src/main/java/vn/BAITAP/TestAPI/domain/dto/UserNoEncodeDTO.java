package vn.BAITAP.TestAPI.domain.dto;

import vn.BAITAP.TestAPI.domain.User;

public class UserNoEncodeDTO {
    private User user;

    private String passwordNoDecode;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPasswordNoDecode() {
        return passwordNoDecode;
    }

    public void setPasswordNoDecode(String passwordNoDecode) {
        this.passwordNoDecode = passwordNoDecode;
    }

}
