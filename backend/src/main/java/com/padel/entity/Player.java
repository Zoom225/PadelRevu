package com.padel.entity;

import com.padel.enums.PlayerLevel;
import com.padel.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private PlayerLevel level = PlayerLevel.DEBUTANT;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private String phoneNumber;

    private String city;

    private Integer ranking = 0;

    @ManyToMany(mappedBy = "players")
    private Set<Match> matches = new HashSet<>();

    // Constructors
    public Player() {}

    public Player(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public PlayerLevel getLevel() { return level; }
    public void setLevel(PlayerLevel level) { this.level = level; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public Integer getRanking() { return ranking; }
    public void setRanking(Integer ranking) { this.ranking = ranking; }
    public Set<Match> getMatches() { return matches; }
    public void setMatches(Set<Match> matches) { this.matches = matches; }
}

