package com.example.application.repository;

import com.example.application.model.Tapahtuma;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TapahtumaRepository extends JpaRepository<Tapahtuma, Long> {
    List<Tapahtuma> findByNimiContainingIgnoreCase(String nimi);
    List<Tapahtuma> findByAikaBetween(LocalDateTime alku, LocalDateTime loppu);
    List<Tapahtuma> findByPaikkaId(Long paikkaId);
    List<Tapahtuma> findByJarjestajaId(Long jarjestajaId);
    List<Tapahtuma> findByPostinumero(int postinumero);
}