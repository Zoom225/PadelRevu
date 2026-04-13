package com.padel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalTime;

@Data
public class SiteRequest {
    @NotBlank private String nom;
    private String adresse;
    @NotNull private Integer nbTerrains;
    private LocalTime heureDebut;
    private LocalTime heureFin;
}
