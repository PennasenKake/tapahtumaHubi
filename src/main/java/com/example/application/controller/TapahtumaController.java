package com.example.application.controller;

import com.example.application.model.Kayttaja;
import com.example.application.model.Tapahtuma;
import com.example.application.repository.KayttajaRepository;
import com.example.application.repository.TapahtumaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tapahtumat")
public class TapahtumaController {

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    @GetMapping
    public List<Tapahtuma> getAllTapahtumat() {
        return tapahtumaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tapahtuma> getTapahtumaById(@PathVariable Long id) {
        Optional<Tapahtuma> tapahtuma = tapahtumaRepository.findById(id);
        return tapahtuma.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Tapahtuma> createTapahtuma(@RequestBody Tapahtuma tapahtuma) {
        if (tapahtuma.getPaikka() == null || tapahtuma.getJarjestaja() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tapahtumaRepository.save(tapahtuma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tapahtuma> updateTapahtuma(@PathVariable Long id, @RequestBody Tapahtuma updatedTapahtuma) {
        Optional<Tapahtuma> existingTapahtuma = tapahtumaRepository.findById(id);
        if (existingTapahtuma.isPresent()) {
            updatedTapahtuma.setId(id);
            return ResponseEntity.ok(tapahtumaRepository.save(updatedTapahtuma));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTapahtuma(@PathVariable Long id) {
        tapahtumaRepository.deleteById(id);
    }

    // @PostMapping("/{id}/osallistujat/{kayttajaId}")
    // public ResponseEntity<Tapahtuma> addOsallistuja(@PathVariable Long id, @PathVariable Long kayttajaId) {
    //     Optional<Tapahtuma> tapahtumaOpt = tapahtumaRepository.findById(id);
    //     Optional<Kayttaja> kayttajaOpt = KayttajaRepository.findById(kayttajaId);
    //     if (tapahtumaOpt.isPresent() && kayttajaOpt.isPresent()) {
    //         Tapahtuma tapahtuma = tapahtumaOpt.get();
    //         tapahtuma.getOsallistujat().add(kayttajaOpt.get());
    //         tapahtumaRepository.save(tapahtuma);
    //         return ResponseEntity.ok(tapahtuma);
    //     }
    //     return ResponseEntity.notFound().build();
    // }
}