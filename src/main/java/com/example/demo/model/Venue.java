package com.example.demo.model;

import com.example.demo.dto.VenueDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "VENUES")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    
    private String city;
    private String country;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Instrument> instruments;

    public Venue(Long id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Venue(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Venue() {

    }

    public void updateFields(Venue venue) {
        this.name = venue.getName();
        this.city = venue.getCity();
        this.country = venue.getCountry();
        this.instruments = venue.getInstruments();
    }

    public static Venue of(VenueDTO venueDTO) {
        return new Venue(venueDTO.getName(), venueDTO.getCity(), venueDTO.getCountry());
    }

    public static Venue of(Long id, VenueDTO venueDTO) {
        return new Venue(id, venueDTO.getName(), venueDTO.getCity(), venueDTO.getCountry());
    }

    public static VenueDTO dto(Venue venue) {
        return new VenueDTO(venue.getName(), venue.getCity(), venue.getCountry(), venue.getId());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
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
