package com.padel.service;

import com.padel.dto.PlayerDTO;
import com.padel.entity.Player;
import com.padel.exception.ResourceNotFoundException;
import com.padel.mapper.PlayerMapper;
import com.padel.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
            .stream().map(playerMapper::toDTO).collect(Collectors.toList());
    }

    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Joueur non trouvé avec l'id: " + id));
        return playerMapper.toDTO(player);
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO dto) {
        Player player = playerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Joueur non trouvé avec l'id: " + id));
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setBirthDate(dto.getBirthDate());
        player.setLevel(dto.getLevel());
        player.setPhoneNumber(dto.getPhoneNumber());
        player.setCity(dto.getCity());
        player.setRanking(dto.getRanking());
        return playerMapper.toDTO(playerRepository.save(player));
    }

    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Joueur non trouvé avec l'id: " + id);
        }
        playerRepository.deleteById(id);
    }
}

