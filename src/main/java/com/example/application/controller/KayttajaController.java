package com.example.application.controller;

import com.example.application.model.Kayttaja;
import com.example.application.repository.KayttajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kayttajat")
public class KayttajaController {

    @Autowired
    private KayttajaRepository kayttajaRepository;

    @GetMapping
    public List<Kayttaja> getAllKayttajat() {
        return kayttajaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kayttaja> getKayttajaById(@PathVariable Long id) {
        Optional<Kayttaja> kayttaja = kayttajaRepository.findById(id);
        return kayttaja.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kayttaja createKayttaja(@RequestBody Kayttaja kayttaja) {
        return kayttajaRepository.save(kayttaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kayttaja> updateKayttaja(@PathVariable Long id, @RequestBody Kayttaja updatedKayttaja) {
        Optional<Kayttaja> existingKayttaja = kayttajaRepository.findById(id);
        if (existingKayttaja.isPresent()) {
            updatedKayttaja.setId(id);
            return ResponseEntity.ok(kayttajaRepository.save(updatedKayttaja));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKayttaja(@PathVariable Long id) {
        kayttajaRepository.deleteById(id);
    }
}