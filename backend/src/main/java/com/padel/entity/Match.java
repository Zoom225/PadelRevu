package com.padel.entity;
import com.padel.enums.MatchStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "matches")
public class Match {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime scheduledAt;
    private String location;
    @Enumerated(EnumType.STRING) private MatchStatus status = MatchStatus.PLANIFIE;
    private Integer scoreTeam1;
    private Integer scoreTeam2;
    @ManyToMany
    @JoinTable(name = "match_players",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players = new HashSet<>();
    @ManyToOne @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    public Match() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public LocalDateTime getScheduledAt() { return scheduledAt; } public void setScheduledAt(LocalDateTime v) { this.scheduledAt = v; }
    public String getLocation() { return location; } public void setLocation(String v) { this.location = v; }
    public MatchStatus getStatus() { return status; } public void setStatus(MatchStatus v) { this.status = v; }
    public Integer getScoreTeam1() { return scoreTeam1; } public void setScoreTeam1(Integer v) { this.scoreTeam1 = v; }
    public Integer getScoreTeam2() { return scoreTeam2; } public void setScoreTeam2(Integer v) { this.scoreTeam2 = v; }
    public Set<Player> getPlayers() { return players; } public void setPlayers(Set<Player> v) { this.players = v; }
    public Tournament getTournament() { return tournament; } public void setTournament(Tournament v) { this.tournament = v; }
}
