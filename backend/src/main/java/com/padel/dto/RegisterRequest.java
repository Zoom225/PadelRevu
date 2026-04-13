package com.padel.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class RegisterRequest {
    @NotBlank @Size(max = 50) private String firstName;
    @NotBlank @Size(max = 50) private String lastName;
    @NotBlank @Email private String email;
    @NotBlank @Size(min = 6) private String password;
    public RegisterRequest() {}
    public String getFirstName() { return firstName; } public void setFirstName(String v) { this.firstName = v; }
    public String getLastName() { return lastName; } public void setLastName(String v) { this.lastName = v; }
    public String getEmail() { return email; } public void setEmail(String v) { this.email = v; }
    public String getPassword() { return password; } public void setPassword(String v) { this.password = v; }
}
