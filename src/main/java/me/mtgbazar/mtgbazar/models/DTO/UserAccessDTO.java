package me.mtgbazar.mtgbazar.models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserAccessDTO {
    @NotBlank(message = "Fill user username")
    @NotNull(message = "Fill user username")
    private String username;
    @Email(message = "Fill valid email")
    @NotBlank(message = "Fill user email")
    @NotNull(message = "Fill user email")
    private String email;
    @NotBlank(message = "Fill user password")
    @NotNull(message = "Fill user password")
    @Size(min = 6, message = "Password has to be at least 6 chars long")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
