package com.example.application.service;

import com.example.application.model.Kayttaja;
import com.example.application.repository.KayttajaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KayttajaService {

    private final KayttajaRepository kayttajaRepository;

    public KayttajaService(KayttajaRepository kayttajaRepository) {
        this.kayttajaRepository = kayttajaRepository;
    }
    

    public List<Kayttaja> findAll() {
        return kayttajaRepository.findAll();
    }

    public Kayttaja findById(Long id) {
        return kayttajaRepository.findById(id).orElse(null);
    }

    public Kayttaja findByNimi(String nimi) {
        return kayttajaRepository.findByNimi(nimi).orElse(null);
    }
    
    public List<Kayttaja> findByRooli(String rooli) {
        return kayttajaRepository.findByRooli(rooli);
    }

    public Kayttaja save(Kayttaja kayttaja) {
        return kayttajaRepository.save(kayttaja);
    }

    public void delete(Long id) {
        kayttajaRepository.deleteById(id);
    }

    public List<Kayttaja> findByTapahtumaId(Long tapahtumaId) {
        return kayttajaRepository.findByTapahtumatId(tapahtumaId);
    }
}