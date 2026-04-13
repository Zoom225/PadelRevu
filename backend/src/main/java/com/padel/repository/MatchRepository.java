package com.padel.repository;
import com.padel.entity.Match;
import com.padel.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByStatus(MatchStatus status);
    List<Match> findByTournamentId(Long tournamentId);
    List<Match> findByPlayers_Id(Long playerId);
}
