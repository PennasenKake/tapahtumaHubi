package com.example.application.data;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SamplePersonRepository
        extends
            JpaRepository<SamplePerson, Long>,
            JpaSpecificationExecutor<SamplePerson> {

    Optional<SamplePerson> findById(Long id);

    SamplePerson save(SamplePerson entity);

    void deleteById(Long id);

    Page<SamplePerson> findAll(Pageable pageable);

    long count();

}
