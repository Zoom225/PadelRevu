package com.padel.dto;

import com.padel.enums.TypeMembre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MembreRequest {
    @NotBlank private String matricule;
    @NotBlank private String nom;
    private Double solde;
    @NotNull private TypeMembre type;
    private Long siteFavoriId;
}
