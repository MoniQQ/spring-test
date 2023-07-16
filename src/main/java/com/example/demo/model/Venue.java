package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Venue {

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "venue")
    private List<Member> members;

    @ManyToMany(mappedBy = "venues")
    private List<Instrument> instruments;

    public Venue(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
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
