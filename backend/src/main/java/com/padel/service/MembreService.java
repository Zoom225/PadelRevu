package com.padel.service;

import com.padel.dto.MembreRequest;
import com.padel.dto.MembreResponse;
import com.padel.entity.Membre;
import com.padel.exception.NotFoundException;
import com.padel.mapper.MembreMapper;
import com.padel.repository.MembreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembreService {
    private final MembreRepository membreRepository;
    private final MembreMapper membreMapper;

    public MembreResponse getByMatricule(String matricule) {
        Membre membre = membreRepository.findByMatricule(matricule)
                .orElseThrow(() -> new NotFoundException("Membre non trouvé"));
        return membreMapper.toResponse(membre);
    }

    public List<MembreResponse> getAll() {
        return membreRepository.findAll().stream()
                .map(membreMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public MembreResponse create(MembreRequest request) {
        if (membreRepository.findByMatricule(request.getMatricule()).isPresent()) {
            throw new IllegalArgumentException("Matricule déjà existant");
        }
        Membre membre = membreMapper.toEntity(request);
        membre.setSolde(request.getSolde() != null ? request.getSolde() : 0.0);
        return membreMapper.toResponse(membreRepository.save(membre));
    }

    @Transactional
    public MembreResponse update(Long id, MembreRequest request) {
        Membre membre = membreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Membre id: " + id));
        membre.setNom(request.getNom());
        membre.setType(request.getType());
        return membreMapper.toResponse(membreRepository.save(membre));
    }

    @Transactional
    public void delete(Long id) {
        membreRepository.deleteById(id);
    }
}
