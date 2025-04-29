package com.example.application.controller;

import com.example.application.model.Paikat;
import com.example.application.repository.PaikatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paikat")
public class PaikkaController {

    @Autowired
    private PaikatRepository paikkaRepository;

    @GetMapping
    public List<Paikat> getAllPaikat() {
        return paikkaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paikat> getPaikkaById(@PathVariable Long id) {
        Optional<Paikat> paikka = paikkaRepository.findById(id);
        return paikka.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paikat createPaikka(@RequestBody Paikat paikka) {
        return paikkaRepository.save(paikka);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paikat> updatePaikka(@PathVariable Long id, @RequestBody Paikat updatedPaikka) {
        Optional<Paikat> existingPaikka = paikkaRepository.findById(id);
        if (existingPaikka.isPresent()) {
            updatedPaikka.setId(id);
            return ResponseEntity.ok(paikkaRepository.save(updatedPaikka));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deletePaikka(@PathVariable Long id) {
        Optional<Paikat> paikka = paikkaRepository.findById(id);
        if (paikka.isPresent()) {
            if (!paikka.get().getTapahtumat().isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Paikkaa ei voi poistaa, koska siihen liittyy tapahtumia.");
            }
            paikkaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}