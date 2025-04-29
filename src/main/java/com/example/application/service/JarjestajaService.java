package com.example.application.service;

import com.example.application.model.Jarjestaja;
import com.example.application.repository.JarjestajaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JarjestajaService {

    private final JarjestajaRepository jarjestajaRepository;

    public JarjestajaService(JarjestajaRepository jarjestajaRepository) {
        this.jarjestajaRepository = jarjestajaRepository;
    }

    public List<Jarjestaja> findAll() {
        return jarjestajaRepository.findAll();
    }

    public Jarjestaja findById(Long id) {
        return jarjestajaRepository.findById(id).orElse(null);
    }

    public Jarjestaja save(Jarjestaja jarjestaja) {
        return jarjestajaRepository.save(jarjestaja);
    }

    public void delete(Long id) {
        jarjestajaRepository.deleteById(id);
    }

    public List<Jarjestaja> findByNimi(String nimi) {
        return jarjestajaRepository.findByNimiContainingIgnoreCase(nimi);
    }

    public List<Jarjestaja> findByTyyppi(String tyyppi) {
        return jarjestajaRepository.findByTyyppi(tyyppi);
    }
}