package com.padel.dto;

import com.padel.enums.StatutPaiement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementResponse {
    private Long id;
    private Long membreId;
    private Long reservationId;
    private Double montant;
    private LocalDateTime datePaiement;
    private StatutPaiement statut;
}
