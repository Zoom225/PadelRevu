package com.padel.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String description;
    private Integer maxPlayers;
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Match> matches = new ArrayList<>();
    public Tournament() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String v) { this.name = v; }
    public LocalDate getStartDate() { return startDate; } public void setStartDate(LocalDate v) { this.startDate = v; }
    public LocalDate getEndDate() { return endDate; } public void setEndDate(LocalDate v) { this.endDate = v; }
    public String getLocation() { return location; } public void setLocation(String v) { this.location = v; }
    public String getDescription() { return description; } public void setDescription(String v) { this.description = v; }
    public Integer getMaxPlayers() { return maxPlayers; } public void setMaxPlayers(Integer v) { this.maxPlayers = v; }
    public List<Match> getMatches() { return matches; } public void setMatches(List<Match> v) { this.matches = v; }
}
