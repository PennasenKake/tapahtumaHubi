package com.example.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ManyToMany;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nimi;
    private String email;
    private String salasana;
    private String rooli;
    private String osoite;
    private int postinumero;
    private String postitoimipaikka;
    private String puhelinnumero;

    @ManyToMany(mappedBy = "osallistujat")
    private List<Tapahtuma> tapahtumat = new ArrayList<>();

    public Kayttaja() {}

    public Kayttaja(Long id, String nimi, String email, String salasana, String rooli, String osoite, int postinumero,
            String postitoimipaikka, String puhelinnumero) {
        this.id = id;
        this.nimi = nimi;
        this.email = email;
        this.salasana = salasana;
        this.rooli = rooli;
        this.osoite = osoite;
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
        this.puhelinnumero = puhelinnumero;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public String getRooli() {
        return rooli;
    }

    public void setRooli(String rooli) {
        this.rooli = rooli;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public int getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(int postinumero) {
        this.postinumero = postinumero;
    }

    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }

    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }


}
