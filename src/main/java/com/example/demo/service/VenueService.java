package com.example.demo.service;

import com.example.demo.model.Instrument;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import com.example.demo.repository.InstrumentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {
    private final InstrumentRepository instrumentRepository;
    private final MemberRepository memberRepository;

    private final VenueRepository venueRepository;


    public VenueService(InstrumentRepository instrumentRepository, MemberRepository memberRepository, VenueRepository venueRepository) {
        this.instrumentRepository = instrumentRepository;
        this.memberRepository = memberRepository;
        this.venueRepository = venueRepository;
    }

    public List<String> getVenueNames() {
        return venueRepository.findAll().stream().map(Venue::getName).toList();
    }

    public Venue saveVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue getVenue(String name) {
        return venueRepository.findByName(name);
    }

    public void deleteVenueByName(String name) {
        Venue venue = getVenue(name);

       // List<Member> members = venue.getMembers();
        // List<Instrument> instruments = venue.getInstruments();

        // memberRepository.deleteAll(members);

        // for (Instrument instrument : instruments) {
        //     List<Venue> newVenues = instrument.getVenues().stream().filter(x -> !x.getName().equals(name)).toList();
        //     instrument.setVenues(newVenues);
        //     instrumentRepository.save(instrument);
        // }

        venueRepository.delete(venue);
    }

    public Member addMemberToVenueByName(String venueName, String memberName) {
        Venue venue = venueRepository.findByName(venueName);
        Member member = memberRepository.findByLegalName(memberName);

       // member.setVenue(venue);

       // List<Member> newMembers = venue.getMembers();
       // newMembers.add(member);

        venueRepository.save(venue);
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMemberByName(String memberName) {
        Member member = memberRepository.findByLegalName(memberName);
       // Venue venue = member.getVenue();

      //  List<Member> newMembers = venue.getMembers();
        //newMembers.remove(member);
       // venueRepository.save(venue);

        memberRepository.delete(member);
    }

    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public Instrument updateInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public void deleteInstrument(Instrument instrument) {
        // List<Venue> venues = instrument.getVenues();

        // venueRepository.saveAll(venues.stream().map(v -> {
        //     List<Instrument> instruments = v.getInstruments().stream().filter(x -> !x.getISIN().equals(instrument.getISIN())).toList();
        //     v.setInstruments(instruments);
        //     return v;
        // }).toList());

        instrumentRepository.delete(instrument);
    }
}
