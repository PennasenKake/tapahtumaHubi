package com.example.application.controller;

import com.example.application.model.Tapahtuma;
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
    public Tapahtuma createTapahtuma(@RequestBody Tapahtuma tapahtuma) {
        return tapahtumaRepository.save(tapahtuma);
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
}