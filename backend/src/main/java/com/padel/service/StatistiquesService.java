package com.padel.service;

import com.padel.dto.StatsCAResponse;
import com.padel.entity.Paiement;
import com.padel.entity.Membre;
import com.padel.enums.StatutPaiement;
import com.padel.repository.PaiementRepository;
import com.padel.repository.MembreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatistiquesService {
    private final PaiementRepository paiementRepository;
    private final MembreRepository membreRepository;

    // Grande action: calculer les statistiques de chiffre d'affaires
    public StatsCAResponse getCA() {
        List<Paiement> paiementsEffectues = paiementRepository.findAll().stream()
                .filter(p -> p.getStatut() == StatutPaiement.EFFECTUE)
                .toList();

        double totalCA = paiementsEffectues.stream()
                .mapToDouble(Paiement::getMontant)
                .sum();

        long nombreMatchesPayes = paiementsEffectues.size();
        double montantMoyen = nombreMatchesPayes > 0 ? totalCA / nombreMatchesPayes : 0.0;

        List<Membre> membres = membreRepository.findAll();
        double soldeTotal = membres.stream()
                .mapToDouble(m -> m.getSolde() != null ? m.getSolde() : 0.0)
                .sum();

        return StatsCAResponse.builder()
                .totalCA(totalCA)
                .nombreMatchesPayes(nombreMatchesPayes)
                .montantMoyen(montantMoyen)
                .soldeTotal(soldeTotal)
                .build();
    }
}

