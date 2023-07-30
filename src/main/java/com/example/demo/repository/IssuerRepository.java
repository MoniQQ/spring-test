package com.example.demo.repository;

import com.example.demo.model.Issuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {
    Optional<Issuer> findById(Long id);
}
