package com.padel.service;

import com.padel.dto.TournamentDTO;
import com.padel.entity.Tournament;
import com.padel.exception.ResourceNotFoundException;
import com.padel.mapper.TournamentMapper;
import com.padel.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    public List<TournamentDTO> getAllTournaments() {
        return tournamentRepository.findAll()
            .stream().map(tournamentMapper::toDTO).collect(Collectors.toList());
    }

    public TournamentDTO getTournamentById(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tournoi non trouvé avec l'id: " + id));
        return tournamentMapper.toDTO(tournament);
    }

    public TournamentDTO createTournament(TournamentDTO dto) {
        Tournament tournament = tournamentMapper.toEntity(dto);
        return tournamentMapper.toDTO(tournamentRepository.save(tournament));
    }

    public TournamentDTO updateTournament(Long id, TournamentDTO dto) {
        Tournament tournament = tournamentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tournoi non trouvé avec l'id: " + id));
        tournament.setName(dto.getName());
        tournament.setStartDate(dto.getStartDate());
        tournament.setEndDate(dto.getEndDate());
        tournament.setLocation(dto.getLocation());
        tournament.setDescription(dto.getDescription());
        tournament.setMaxPlayers(dto.getMaxPlayers());
        return tournamentMapper.toDTO(tournamentRepository.save(tournament));
    }

    public void deleteTournament(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tournoi non trouvé avec l'id: " + id);
        }
        tournamentRepository.deleteById(id);
    }
}

