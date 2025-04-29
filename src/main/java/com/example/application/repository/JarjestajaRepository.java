package com.example.application.repository;

import com.example.application.model.Jarjestaja;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JarjestajaRepository extends JpaRepository<Jarjestaja, Long> {
    List<Jarjestaja> findByNimiContainingIgnoreCase(String nimi);
    List<Jarjestaja> findByTyyppi(String tyyppi);
}