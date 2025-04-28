package com.example.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 

@Entity
public class Jarjestaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jarjestajaNimi;
    private String jarjestajaOsoite;
    private int jarjestajaPostinumero;
    private String jarjestajaPostitoimipaikka;
    private int jarjestajaPuhelinnumero;

    public Jarjestaja() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJarjestajaNimi() {
        return jarjestajaNimi;
    }

    public void setJarjestajaNimi(String jarjestajaNimi) {
        this.jarjestajaNimi = jarjestajaNimi;
    }

    public String getJarjestajaOsoite() {
        return jarjestajaOsoite;
    }

    public void setJarjestajaOsoite(String jarjestajaOsoite) {
        this.jarjestajaOsoite = jarjestajaOsoite;
    }

    public int getJarjestajaPostinumero() {
        return jarjestajaPostinumero;
    }

    public void setJarjestajaPostinumero(int jarjestajaPostinumero) {
        this.jarjestajaPostinumero = jarjestajaPostinumero;
    }

    public String getJarjestajaPostitoimipaikka() {
        return jarjestajaPostitoimipaikka;
    }

    public void setJarjestajaPostitoimipaikka(String jarjestajaPostitoimipaikka) {
        this.jarjestajaPostitoimipaikka = jarjestajaPostitoimipaikka;
    }

    public int getJarjestajaPuhelinnumero() {
        return jarjestajaPuhelinnumero;
    }

    public void setJarjestajaPuhelinnumero(int jarjestajaPuhelinnumero) {
        this.jarjestajaPuhelinnumero = jarjestajaPuhelinnumero;
    }


    public Jarjestaja(Long id, String jarjestajaNimi, String jarjestajaOsoite, int jarjestajaPostinumero, String jarjestajaPostitoimipaikka, int jarjestajaPuhelinnumero) {
        this.id = id;
        this.jarjestajaNimi = jarjestajaNimi;
        this.jarjestajaOsoite = jarjestajaOsoite;
        this.jarjestajaPostinumero = jarjestajaPostinumero;
        this.jarjestajaPostitoimipaikka = jarjestajaPostitoimipaikka;
        this.jarjestajaPuhelinnumero = jarjestajaPuhelinnumero;
    }

    


    
}
