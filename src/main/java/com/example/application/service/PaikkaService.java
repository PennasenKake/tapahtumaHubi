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

    public Paikat save(Paikat paikka) {
        return paikkaRepository.save(paikka);
    }

    public List<Paikat> findAll() {
        return paikkaRepository.findAll();
    }
}
