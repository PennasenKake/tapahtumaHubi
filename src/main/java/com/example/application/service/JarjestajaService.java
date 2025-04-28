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

    public Jarjestaja save(Jarjestaja jarjestaja) {
        return jarjestajaRepository.save(jarjestaja);
    }

    public Jarjestaja findById(Long id) {
        return jarjestajaRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        jarjestajaRepository.deleteById(id);
    }
}