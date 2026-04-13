package com.padel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "terrains")
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    private boolean couvert;
    private boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    public Terrain() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public boolean isCouvert() { return couvert; }
    public void setCouvert(boolean couvert) { this.couvert = couvert; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }
}

