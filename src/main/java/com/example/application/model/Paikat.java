package com.example.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 

@Entity
public class Paikat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private String paikkaOsoite;
    private int paikkaPostinumero;
    private String paikkaPostitoimipaikka;

    public Paikat(Long id, String nimi, String paikkaOsoite, int paikkaPostinumero, String paikkaPostitoimipaikka) {
        this.id = id;
        this.nimi = nimi;
        this.paikkaOsoite = paikkaOsoite;
        this.paikkaPostinumero = paikkaPostinumero;
        this.paikkaPostitoimipaikka = paikkaPostitoimipaikka;
    }

    public Paikat() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNimi() {
        return nimi;
    }
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public String getPaikkaOsoite() {
        return paikkaOsoite;
    }
    public void setPaikkaOsoite(String paikkaOsoite) {
        this.paikkaOsoite = paikkaOsoite;
    }
    public int getPaikkaPostinumero() {
        return paikkaPostinumero;
    }
    public void setPaikkaPostinumero(int paikkaPostinumero) {
        this.paikkaPostinumero = paikkaPostinumero;
    }
    public String getPaikkaPostitoimipaikka() {
        return paikkaPostitoimipaikka;
    }
    public void setPaikkaPostitoimipaikka(String paikkaPostitoimipaikka) {
        this.paikkaPostitoimipaikka = paikkaPostitoimipaikka;
    }
}