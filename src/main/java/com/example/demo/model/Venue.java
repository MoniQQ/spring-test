package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;
    private String city;
    private String country;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    @OneToMany
    private List<Member> members;

    @ManyToMany
    private List<Instrument> instruments;

    public Venue(Long id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.members = new ArrayList<Member>();
        this.instruments = new ArrayList<Instrument>();
    }

    public Venue(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.members = new ArrayList<Member>();
        this.instruments = new ArrayList<Instrument>();
    }

    public Venue() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
