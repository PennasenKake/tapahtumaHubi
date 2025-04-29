package com.example.application.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Paikat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String osoite;
    private Integer kapasiteetti;
    private Integer postinumero;
    private String postitoimipaikka;

    @OneToMany(mappedBy = "paikka")
    private List<Tapahtuma> tapahtumat = new ArrayList<>();

    // Constructors
    public Paikat() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }
    public String getOsoite() { return osoite; }
    public void setOsoite(String osoite) { this.osoite = osoite; }
    public Integer getKapasiteetti() { return kapasiteetti; }
    public void setKapasiteetti(Integer kapasiteetti) { this.kapasiteetti = kapasiteetti; }
    public Integer getPostinumero() { return postinumero; }
    public void setPostinumero(Integer postinumero) { this.postinumero = postinumero; }
    public String getPostitoimipaikka() { return postitoimipaikka; }
    public void setPostitoimipaikka(String postitoimipaikka) { this.postitoimipaikka = postitoimipaikka; }
    public List<Tapahtuma> getTapahtumat() { return tapahtumat; }
    public void setTapahtumat(List<Tapahtuma> tapahtumat) { this.tapahtumat = tapahtumat; }
}