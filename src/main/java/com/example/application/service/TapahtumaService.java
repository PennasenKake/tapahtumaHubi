package com.example.application.service;

import com.example.application.model.Tapahtuma;
import com.example.application.repository.TapahtumaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TapahtumaService {

    private final TapahtumaRepository tapahtumaRepository;

    public TapahtumaService(TapahtumaRepository tapahtumaRepository) {
        this.tapahtumaRepository = tapahtumaRepository;
    }

    public List<Tapahtuma> findAll() {
        return tapahtumaRepository.findAll();
    }

    public Tapahtuma findById(Long id) {
        return tapahtumaRepository.findById(id).orElse(null);
    }

    public Tapahtuma save(Tapahtuma tapahtuma) {
        return tapahtumaRepository.save(tapahtuma);
    }

    public void delete(Long id) {
        tapahtumaRepository.deleteById(id);
    }

    public List<Tapahtuma> findByNimi(String nimi) {
        return tapahtumaRepository.findByNimiContainingIgnoreCase(nimi);
    }

    public List<Tapahtuma> findByAikaBetween(LocalDateTime alku, LocalDateTime loppu) {
        return tapahtumaRepository.findByAikaBetween(alku, loppu);
    }

    public List<Tapahtuma> findByPaikkaId(Long paikkaId) {
        return tapahtumaRepository.findByPaikkaId(paikkaId);
    }

    public List<Tapahtuma> findByJarjestajaId(Long jarjestajaId) {
        return tapahtumaRepository.findByJarjestajaId(jarjestajaId);
    }

    public List<Tapahtuma> findByPostinumero(int postinumero) {
        return tapahtumaRepository.findByPostinumero(postinumero);
    }
}