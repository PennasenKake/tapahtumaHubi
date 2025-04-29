package com.example.application.service;

import com.example.application.model.Paikat;
import com.example.application.repository.PaikatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaikkaService {

    private final PaikatRepository paikkaRepository;

    public PaikkaService(PaikatRepository paikkaRepository) {
        this.paikkaRepository = paikkaRepository;
    }

    public List<Paikat> findAll() {
        return paikkaRepository.findAll();
    }

    public Paikat findById(Long id) {
        return paikkaRepository.findById(id).orElse(null);
    }

    public Paikat save(Paikat paikka) {
        return paikkaRepository.save(paikka);
    }

    public void delete(Long id) {
        Paikat paikka = findById(id);
        if (paikka != null && !paikka.getTapahtumat().isEmpty()) {
            throw new IllegalStateException("Paikkaa ei voi poistaa, koska siihen liittyy tapahtumia.");
        }
        paikkaRepository.deleteById(id);
    }

    public List<Paikat> findByNimi(String nimi) {
        return paikkaRepository.findByNimiContainingIgnoreCase(nimi);
    }

    public List<Paikat> findByKapasiteettiGreaterThanEqual(Integer kapasiteetti) {
        return paikkaRepository.findByKapasiteettiGreaterThanEqual(kapasiteetti);
    }
}