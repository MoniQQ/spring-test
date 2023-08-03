package com.example.demo.repository;

import com.example.demo.model.Instrument;
import com.example.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Optional<Instrument> findById(Long id);

    boolean existsByISIN(String isin);
}
