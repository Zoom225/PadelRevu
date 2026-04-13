package com.padel.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class SiteResponse {
    private Long id;
    private String nom;
    private String adresse;
    private Integer nbTerrains;
    private LocalTime heureDebut;
    private LocalTime heureFin;
}
