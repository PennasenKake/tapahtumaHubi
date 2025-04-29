package com.example.application.controller;

import com.example.application.model.Jarjestaja;
import com.example.application.repository.JarjestajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jarjestajat")
public class JarjestajaController {

    @Autowired
    private JarjestajaRepository jarjestajaRepository;

    @GetMapping
    public List<Jarjestaja> getAllJarjestajat() {
        return jarjestajaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jarjestaja> getJarjestajaById(@PathVariable Long id) {
        Optional<Jarjestaja> jarjestaja = jarjestajaRepository.findById(id);
        return jarjestaja.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Jarjestaja createJarjestaja(@RequestBody Jarjestaja jarjestaja) {
        return jarjestajaRepository.save(jarjestaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jarjestaja> updateJarjestaja(@PathVariable Long id, @RequestBody Jarjestaja updatedJarjestaja) {
        Optional<Jarjestaja> existingJarjestaja = jarjestajaRepository.findById(id);
        if (existingJarjestaja.isPresent()) {
            updatedJarjestaja.setId(id);
            return ResponseEntity.ok(jarjestajaRepository.save(updatedJarjestaja));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteJarjestaja(@PathVariable Long id) {
        Optional<Jarjestaja> jarjestaja = jarjestajaRepository.findById(id);
        if (jarjestaja.isPresent()) {
            if (jarjestaja.get().getTapahtuma() != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Järjestäjää ei voi poistaa, koska siihen liittyy tapahtuma.");
            }
            jarjestajaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}