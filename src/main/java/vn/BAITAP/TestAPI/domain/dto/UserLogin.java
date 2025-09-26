package vn.BAITAP.TestAPI.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLogin {
    @NotBlank(message = "You have to write the Email")
    private String email;
    @NotBlank(message = "You have to write the Password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
