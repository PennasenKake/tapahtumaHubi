package com.example.application.repository;

import com.example.application.model.Paikat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaikatRepository extends JpaRepository<Paikat, Long> {
    List<Paikat> findByNimiContainingIgnoreCase(String nimi);
    List<Paikat> findByKapasiteettiGreaterThanEqual(Integer kapasiteetti);
}