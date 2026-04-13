package com.padel.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaiementRequest {
    @NotNull private Long reservationId;
    @Positive private Double montant;
}
