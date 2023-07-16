package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Instrument {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String ISIN;
    private Currency currency;
    private InstrumentType type;
    private String description;
    private LocalDateTime effectiveDate;

    @ManyToMany(mappedBy = "instruments")
    private List<Venue> venues;

    public Instrument(Long id, String isin, Currency currency, InstrumentType type, String description, LocalDateTime effectiveDate, List<Venue> venues) {
        ISIN = isin;
        this.currency = currency;
        this.type = type;
        this.description = description;
        this.effectiveDate = effectiveDate;
        this.venues = venues;
    }

    public Instrument() {

    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public InstrumentType getType() {
        return type;
    }

    public void setType(InstrumentType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}