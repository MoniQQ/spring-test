package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ISSUERS")
public class Issuer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Issuer() {}

    public Issuer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
