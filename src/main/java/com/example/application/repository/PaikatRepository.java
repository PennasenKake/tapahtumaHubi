package com.example.application.repository;

import com.example.application.model.Paikat; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaikatRepository extends JpaRepository<Paikat, Long> {
}