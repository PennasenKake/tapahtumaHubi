package com.example.application.model;

import jakarta.persistence.*;

@Entity
public class Jarjestaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String yhteystiedot;
    private String tyyppi; // Lisätään tyyppi tehtävänannon mukaisesti

    @OneToOne(mappedBy = "jarjestaja")
    private Tapahtuma tapahtuma;

    // Constructors
    public Jarjestaja() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }
    public String getYhteystiedot() { return yhteystiedot; }
    public void setYhteystiedot(String yhteystiedot) { this.yhteystiedot = yhteystiedot; }
    public String getTyyppi() { return tyyppi; }
    public void setTyyppi(String tyyppi) { this.tyyppi = tyyppi; }
    public Tapahtuma getTapahtuma() { return tapahtuma; }
    public void setTapahtuma(Tapahtuma tapahtuma) { this.tapahtuma = tapahtuma; }
}