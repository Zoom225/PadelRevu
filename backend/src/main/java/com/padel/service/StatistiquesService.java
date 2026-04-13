package com.padel.service;

import com.padel.dto.StatsCAResponse;
import com.padel.repository.PaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatistiquesService {
    private final PaiementRepository paiementRepository;

    // Grande action: calculer les statistiques de chiffre d'affaires
    public StatsCAResponse getCA() {
        Double ca = paiementRepository.findAll().stream().mapToDouble(p -> p.getMontant()).sum();
        StatsCAResponse res = new StatsCAResponse();
        res.setChiffreAffaires(ca);
        res.setNombreMatches((long) paiementRepository.count());
        return res;
    }
}
