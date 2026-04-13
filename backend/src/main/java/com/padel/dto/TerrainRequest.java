package com.padel.dto;

import jakarta.validation.constraints.NotBlank;

public class TerrainRequest {

    @NotBlank
    private String nom;

    private boolean couvert;
    private boolean disponible = true;
    private Long siteId;

    public TerrainRequest() {}

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public boolean isCouvert() { return couvert; }
    public void setCouvert(boolean couvert) { this.couvert = couvert; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public Long getSiteId() { return siteId; }
    public void setSiteId(Long siteId) { this.siteId = siteId; }
}

