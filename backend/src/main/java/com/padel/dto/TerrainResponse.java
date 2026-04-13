package com.padel.dto;

public class TerrainResponse {

    private Long id;
    private String nom;
    private boolean couvert;
    private boolean disponible;
    private Long siteId;
    private String siteNom;

    public TerrainResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public boolean isCouvert() { return couvert; }
    public void setCouvert(boolean couvert) { this.couvert = couvert; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public Long getSiteId() { return siteId; }
    public void setSiteId(Long siteId) { this.siteId = siteId; }
    public String getSiteNom() { return siteNom; }
    public void setSiteNom(String siteNom) { this.siteNom = siteNom; }
}

