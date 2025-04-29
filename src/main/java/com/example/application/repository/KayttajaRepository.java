package com.example.application.repository;

import com.example.application.model.Kayttaja;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface KayttajaRepository extends JpaRepository<Kayttaja, Long> {
    Optional<Kayttaja> findByNimi(String nimi);
    List<Kayttaja> findByRooli(String rooli);
    List<Kayttaja> findByTapahtumatId(Long tapahtumaId);
}