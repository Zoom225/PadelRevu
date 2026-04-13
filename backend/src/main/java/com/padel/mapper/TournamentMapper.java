package com.padel.mapper;
import com.padel.dto.TournamentDTO;
import com.padel.entity.Tournament;
import org.springframework.stereotype.Component;
@Component
public class TournamentMapper {
    public TournamentDTO toDTO(Tournament tournament) {
        TournamentDTO dto = new TournamentDTO();
        dto.setId(tournament.getId());
        dto.setName(tournament.getName());
        dto.setStartDate(tournament.getStartDate());
        dto.setEndDate(tournament.getEndDate());
        dto.setLocation(tournament.getLocation());
        dto.setDescription(tournament.getDescription());
        dto.setMaxPlayers(tournament.getMaxPlayers());
        return dto;
    }
    public Tournament toEntity(TournamentDTO dto) {
        Tournament t = new Tournament();
        t.setName(dto.getName());
        t.setStartDate(dto.getStartDate());
        t.setEndDate(dto.getEndDate());
        t.setLocation(dto.getLocation());
        t.setDescription(dto.getDescription());
        t.setMaxPlayers(dto.getMaxPlayers());
        return t;
    }
}
