package com.example.demo.service;

import com.example.demo.model.Instrument;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import com.example.demo.repository.InstrumentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.VenueRepository;
import com.example.demo.service.exception.MemberNotFoundException;
import com.example.demo.service.exception.VenueNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService {
    @Autowired
    private InstrumentRepository instrumentRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VenueRepository venueRepository;

    public VenueService(InstrumentRepository instrumentRepository, MemberRepository memberRepository, VenueRepository venueRepository) {
        this.instrumentRepository = instrumentRepository;
        this.memberRepository = memberRepository;
        this.venueRepository = venueRepository;
    }

    public List<String> getVenueNames() {
        return venueRepository.findAll().stream().map(Venue::getName).collect(Collectors.toList());
    }

    public Venue saveVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElseThrow(VenueNotFoundException::new);
    }

    public Venue getVenueByName(String name) {
        return venueRepository.findByName(name).orElseThrow(VenueNotFoundException::new);
    }

    public Venue updateVenueDetails(Venue venue) {
        return venueRepository.save(
                getVenueById(venue.getId())
        );
    }
 
    public void deleteVenueByName(String name) {
        Venue venue = getVenueByName(name);

        List<Instrument> instruments = venue.getInstruments();

        for (Instrument instrument : instruments) {
            List<Venue> newVenues = instrument.getVenues().stream().filter(x -> !x.getName().equals(name)).collect(Collectors.toList());
            instrument.setVenues(newVenues);
            instrumentRepository.save(instrument);
        }

        venueRepository.delete(venue);
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberByName(String legalName) {
        return memberRepository.findByLegalName(legalName).orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Member addMemberToVenueByName(String venueName, String memberName) {
        Member member = getMemberByName(memberName);
        Venue venue = getVenueByName(venueName);

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        return memberRepository.save(
                getMemberById(member.getId())
        );
    }

    public void deleteMemberByName(String memberName) {
        Member member = getMemberByName(memberName);

        memberRepository.delete(member);
    }

    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public Instrument updateInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public void deleteInstrument(Instrument instrument) {
        List<Venue> venues = instrument.getVenues();

        venueRepository.saveAll(venues.stream().map(v -> {
            List<Instrument> instruments = v.getInstruments().stream().filter(x -> !x.getISIN().equals(instrument.getISIN())).collect(Collectors.toList());
            v.setInstruments(instruments);
            return v;
        }).collect(Collectors.toList()));

        instrumentRepository.delete(instrument);
    }

}
