package com.padel.dto;

import com.padel.enums.TypeMembre;
import lombok.Data;

@Data
public class MembreResponse {
    private Long id;
    private String matricule;
    private String nom;
    private Double solde;
    private TypeMembre type;
    private Long siteFavoriId;
    private String siteFavoriNom;
}
