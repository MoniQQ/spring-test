package com.example.demo.model;

import com.example.demo.dto.MemberDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "MEMBERS")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String lei;

    @Column(unique = true)
    private String name;

    private String description;

    private String address;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", nullable = true)
    private Venue venue;

    public Member(Long id, String lei, String name, String description, String address, Venue venue) {
        this.id = id;
        this.lei = lei;
        this.name = name;
        this.description = description;
        this.address = address;
        this.venue = venue;
    }

    public Member() {

    }

    public void updateFields(Member member) {
        this.lei = member.getLei();
        this.name = member.getName();
        this.description = member.getDescription();
        this.address = member.getAddress();
        this.venue = member.getVenue();
    }

    public static Member of(Long id, MemberDTO memberDTO, Venue venue) {
        return new Member(id, memberDTO.getLei(), memberDTO.getName(), memberDTO.getDescription(), memberDTO.getAddress(), venue);
    }

    public static MemberDTO dto(Member member) {
        return new MemberDTO(member.lei, member.name, member.description, member.address, member.getVenueName());
    }

    public Venue getVenue() {
        return venue;
    }

    public String getVenueName() {
        return this.venue == null ? null : this.venue.getName();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
