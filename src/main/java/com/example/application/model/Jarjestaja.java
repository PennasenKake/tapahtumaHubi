package com.example.application.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jarjestaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String yhteystiedot;

    @OneToMany(mappedBy = "jarjestaja")
    private List<Tapahtuma> tapahtumat = new ArrayList<>();

    // Constructors
    public Jarjestaja() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }
    public String getYhteystiedot() { return yhteystiedot; }
    public void setYhteystiedot(String yhteystiedot) { this.yhteystiedot = yhteystiedot; }
    public List<Tapahtuma> getTapahtumat() { return tapahtumat; }
    public void setTapahtumat(List<Tapahtuma> tapahtumat) { this.tapahtumat = tapahtumat; }
}