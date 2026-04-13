package com.padel.service;

import com.padel.dto.PaiementRequest;
import com.padel.dto.PaiementResponse;
import com.padel.entity.*;
import com.padel.enums.StatutPaiement;
import com.padel.exception.NotFoundException;
import com.padel.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaiementService {
    private final PaiementRepository paiementRepository;
    private final ReservationRepository reservationRepository;
    private final MembreRepository membreRepository;
    private final ParticipantRepository participantRepository;

    // Grande action: payer sa part de match
    @Transactional
    public PaiementResponse payerPart(String matricule, PaiementRequest request) {
        Membre membre = membreRepository.findByMatricule(matricule)
                .orElseThrow(() -> new NotFoundException("Membre"));
        Reservation reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new NotFoundException("Réservation"));
        Participant participant = participantRepository.findByReservationIdAndMembreId(reservation.getId(), membre.getId())
                .orElseThrow(() -> new IllegalArgumentException("Non inscrit"));
        if (participant.getAPaye()) throw new IllegalArgumentException("Déjà payé");

        double montant = request.getMontant();
        // Rembourser d'abord le solde du membre
        if (membre.getSolde() > 0) {
            double remboursement = Math.min(montant, membre.getSolde());
            membre.setSolde(membre.getSolde() - remboursement);
            montant -= remboursement;
        }
        if (montant < 15.0 && membre.getSolde() == 0)
            throw new IllegalArgumentException("Montant minimum 15€");
        if (montant > 15.0) {
            membre.setSolde(membre.getSolde() + (montant - 15.0));
            montant = 15.0;
        }
        Paiement paiement = Paiement.builder()
                .montant(request.getMontant())
                .datePaiement(LocalDateTime.now())
                .statut(StatutPaiement.EFFECTUE)
                .membre(membre)
                .reservation(reservation)
                .build();
        paiementRepository.save(paiement);
        participant.setAPaye(true);
        participantRepository.save(participant);
        reservation.setMontantPaye(reservation.getMontantPaye() + 15.0);
        reservationRepository.save(reservation);
        membreRepository.save(membre);
        // mapper vers response (simplifié)
        PaiementResponse response = new PaiementResponse();
        response.setId(paiement.getId());
        response.setMontant(paiement.getMontant());
        response.setDatePaiement(paiement.getDatePaiement());
        response.setStatut(paiement.getStatut());
        response.setMembreId(membre.getId());
        response.setMembreMatricule(membre.getMatricule());
        response.setReservationId(reservation.getId());
        return response;
    }
}
