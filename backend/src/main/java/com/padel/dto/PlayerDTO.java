package com.padel.dto;
import com.padel.enums.PlayerLevel;
import com.padel.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
public class PlayerDTO {
    private Long id;
    @NotBlank @Size(max = 50) private String firstName;
    @NotBlank @Size(max = 50) private String lastName;
    @NotBlank @Email private String email;
    private LocalDate birthDate;
    private PlayerLevel level;
    private Role role;
    private String phoneNumber;
    private String city;
    private Integer ranking;
    public PlayerDTO() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; } public void setFirstName(String v) { this.firstName = v; }
    public String getLastName() { return lastName; } public void setLastName(String v) { this.lastName = v; }
    public String getEmail() { return email; } public void setEmail(String v) { this.email = v; }
    public LocalDate getBirthDate() { return birthDate; } public void setBirthDate(LocalDate v) { this.birthDate = v; }
    public PlayerLevel getLevel() { return level; } public void setLevel(PlayerLevel v) { this.level = v; }
    public Role getRole() { return role; } public void setRole(Role v) { this.role = v; }
    public String getPhoneNumber() { return phoneNumber; } public void setPhoneNumber(String v) { this.phoneNumber = v; }
    public String getCity() { return city; } public void setCity(String v) { this.city = v; }
    public Integer getRanking() { return ranking; } public void setRanking(Integer v) { this.ranking = v; }
}
