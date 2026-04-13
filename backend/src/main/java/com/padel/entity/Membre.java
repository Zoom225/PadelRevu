package com.padel.entity;

import com.padel.enums.TypeMembre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "membres")
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String matricule;

    @NotBlank
    private String nom;

    private Double solde = 0.0;

    @Enumerated(EnumType.STRING)
    private TypeMembre type;

    @ManyToOne
    @JoinColumn(name = "site_favori_id")
    private Site siteFavori;

    private boolean actif = true;
}

