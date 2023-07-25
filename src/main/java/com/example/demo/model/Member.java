package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String lei;

    @Column(unique = true)
    private String legalName;

    private String description;

    private String address;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", nullable = true)
    private Venue venue;

    public Member(Long id, String lei, String legalName, String description, String address, Venue venue) {
        this.id = id;
        this.lei = lei;
        this.legalName = legalName;
        this.description = description;
        this.address = address;
        this.venue = venue;
    }

    public Member() {

    }

    public static Member of(MemberDTO memberDTO) {
        return new Member(memberDTO.getId(), memberDTO.getLei(), memberDTO.getLegalName(), memberDTO.getDescription(), memberDTO.getAddress(), memberDTO.getVenue());
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getLei() {
        return lei;
    }

    public void setLei(String lei) {
        this.lei = lei;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
