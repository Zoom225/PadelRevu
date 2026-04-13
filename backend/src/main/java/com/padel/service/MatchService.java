package com.padel.service;

import com.padel.dto.MatchDTO;
import com.padel.entity.Match;
import com.padel.entity.Player;
import com.padel.entity.Tournament;
import com.padel.enums.MatchStatus;
import com.padel.exception.ResourceNotFoundException;
import com.padel.mapper.MatchMapper;
import com.padel.repository.MatchRepository;
import com.padel.repository.PlayerRepository;
import com.padel.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, PlayerRepository playerRepository,
                        TournamentRepository tournamentRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchMapper = matchMapper;
    }

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream().map(matchMapper::toDTO).collect(Collectors.toList());
    }

    public MatchDTO getMatchById(Long id) {
        Match match = matchRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Match non trouvé avec l'id: " + id));
        return matchMapper.toDTO(match);
    }

    public MatchDTO createMatch(MatchDTO dto) {
        Match match = new Match();
        match.setScheduledAt(dto.getScheduledAt());
        match.setLocation(dto.getLocation());
        match.setStatus(dto.getStatus() != null ? dto.getStatus() : MatchStatus.PLANIFIE);
        if (dto.getPlayerIds() != null && !dto.getPlayerIds().isEmpty()) {
            Set<Player> players = new HashSet<>(playerRepository.findAllById(dto.getPlayerIds()));
            match.setPlayers(players);
        }
        if (dto.getTournamentId() != null) {
            Tournament tournament = tournamentRepository.findById(dto.getTournamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Tournoi non trouvé: " + dto.getTournamentId()));
            match.setTournament(tournament);
        }
        return matchMapper.toDTO(matchRepository.save(match));
    }

    public MatchDTO updateScore(Long id, Integer scoreTeam1, Integer scoreTeam2) {
        Match match = matchRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Match non trouvé avec l'id: " + id));
        match.setScoreTeam1(scoreTeam1);
        match.setScoreTeam2(scoreTeam2);
        match.setStatus(MatchStatus.TERMINE);
        return matchMapper.toDTO(matchRepository.save(match));
    }

    public List<MatchDTO> getMatchesByPlayer(Long playerId) {
        return matchRepository.findByPlayers_Id(playerId)
            .stream().map(matchMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Match non trouvé avec l'id: " + id);
        }
        matchRepository.deleteById(id);
    }
}

