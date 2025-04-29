package com.example.application.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tapahtuma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;
    private LocalDateTime aika;
    private String osoite;
    private int postinumero;
    private String postitoimipaikka;
    private String puhelinnumero;
    private String kuvaus;

    @ManyToOne
    @JoinColumn(name = "paikka_id")
    private Paikat paikka;

    @OneToOne
    @JoinColumn(name = "jarjestaja_id", unique = true)
    private Jarjestaja jarjestaja;

    @ManyToMany
    @JoinTable(
        name = "tapahtuma_kayttaja",
        joinColumns = @JoinColumn(name = "tapahtuma_id"),
        inverseJoinColumns = @JoinColumn(name = "kayttaja_id")
    )
    private List<Kayttaja> osallistujat = new ArrayList<>();

    // Constructors
    public Tapahtuma() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }
    public LocalDateTime getAika() { return aika; }
    public void setAika(LocalDateTime aika) { this.aika = aika; }
    public String getOsoite() { return osoite; }
    public void setOsoite(String osoite) { this.osoite = osoite; }
    public int getPostinumero() { return postinumero; }
    public void setPostinumero(int postinumero) { this.postinumero = postinumero; }
    public String getPostitoimipaikka() { return postitoimipaikka; }
    public void setPostitoimipaikka(String postitoimipaikka) { this.postitoimipaikka = postitoimipaikka; }
    public String getPuhelinnumero() { return puhelinnumero; }
    public void setPuhelinnumero(String puhelinnumero) { this.puhelinnumero = puhelinnumero; }
    public String getKuvaus() { return kuvaus; }
    public void setKuvaus(String kuvaus) { this.kuvaus = kuvaus; }
    public Paikat getPaikka() { return paikka; }
    public void setPaikka(Paikat paikka) { this.paikka = paikka; }
    public Jarjestaja getJarjestaja() { return jarjestaja; }
    public void setJarjestaja(Jarjestaja jarjestaja) { this.jarjestaja = jarjestaja; }
    public List<Kayttaja> getOsallistujat() { return osallistujat; }
    public void setOsallistujat(List<Kayttaja> osallistujat) { this.osallistujat = osallistujat; }
}
