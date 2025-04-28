package com.example.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 

@Entity
public class Tapahtuma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tapahtumanNimi;
    private String tapahtumanOsoite;
    private int tapahtumanPostinumero;
    private String tapahtumanPostitoimipaikka;
    private int tapahtumanPuhelinnumero;
    private String tapahtumanKuvaus;

    public Tapahtuma() {}

    public Tapahtuma(Long id, String tapahtumanNimi, String tapahtumanOsoite, int tapahtumanPostinumero, String tapahtumanPostitoimipaikka, int tapahtumanPuhelinnumero, String tapahtumanKuvaus) {
        this.id = id;
        this.tapahtumanNimi = tapahtumanNimi;
        this.tapahtumanOsoite = tapahtumanOsoite;
        this.tapahtumanPostinumero = tapahtumanPostinumero;
        this.tapahtumanPostitoimipaikka = tapahtumanPostitoimipaikka;
        this.tapahtumanPuhelinnumero = tapahtumanPuhelinnumero;
        this.tapahtumanKuvaus = tapahtumanKuvaus;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTapahtumanNimi() {
        return tapahtumanNimi;
    }
    public void setTapahtumanNimi(String tapahtumanNimi) {
        this.tapahtumanNimi = tapahtumanNimi;
    }
    public String getTapahtumanOsoite() {
        return tapahtumanOsoite;
    }
    public void setTapahtumanOsoite(String tapahtumanOsoite) {
        this.tapahtumanOsoite = tapahtumanOsoite;
    }
    public int getTapahtumanPostinumero() {
        return tapahtumanPostinumero;
    }
    public void setTapahtumanPostinumero(int tapahtumanPostinumero) {
        this.tapahtumanPostinumero = tapahtumanPostinumero;
    }
    public String getTapahtumanPostitoimipaikka() {
        return tapahtumanPostitoimipaikka;
    }
    public void setTapahtumanPostitoimipaikka(String tapahtumanPostitoimipaikka) {
        this.tapahtumanPostitoimipaikka = tapahtumanPostitoimipaikka;
    }
    public int getTapahtumanPuhelinnumero() {
        return tapahtumanPuhelinnumero;
    }
    public void setTapahtumanPuhelinnumero(int tapahtumanPuhelinnumero) {
        this.tapahtumanPuhelinnumero = tapahtumanPuhelinnumero;
    }
    public String getTapahtumanKuvaus() {
        return tapahtumanKuvaus;
    }
    public void setTapahtumanKuvaus(String tapahtumanKuvaus) {
        this.tapahtumanKuvaus = tapahtumanKuvaus;
    }


    

}
