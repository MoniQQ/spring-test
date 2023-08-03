package com.example.demo.service;

import com.example.demo.model.Instrument;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import com.example.demo.repository.InstrumentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.VenueRepository;
import com.example.demo.service.exception.AlreadyExistsException;
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

    public List<Venue> getVenues() {
        return venueRepository.findAll();
    }

    public List<String> getVenueNames() {
        return getVenues().stream().map(Venue::getName).collect(Collectors.toList());
    }

    public Venue createVenue(Venue venue) {
        if (venueRepository.existsByName(venue.getName())) throw new AlreadyExistsException(venue);

        return venueRepository.save(venue);
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElseThrow(VenueNotFoundException::new);
    }

    public Venue getVenueByName(String name) {
        return venueRepository.findByName(name).orElseThrow(VenueNotFoundException::new);
    }

    public Venue updateVenue(Venue venue) {
        Venue existingVenue = getVenueById(venue.getId());

        existingVenue.updateFields(venue);

        return venueRepository.save(existingVenue);
    }
 
    public Venue deleteVenueById(Long id) {
        Venue venue = getVenueById(id);
        venueRepository.delete(venue);

        return venue;
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public List<Member> getVenueMembers(Long id) {
        String venueName = getVenueById(id).getName();

        return getMembers().stream().filter(x -> x.getVenueName().equals(venueName)).collect(Collectors.toList());
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public Member createMember(Member member) {
        if (memberRepository.existsByLei(member.getLei())) throw new AlreadyExistsException(member);

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member existingMember = getMemberById(member.getId());

        existingMember.updateFields(member);

        return memberRepository.save(existingMember);
    }

    public Member deleteMemberById(Long id) {
        Member member = getMemberById(id);

        memberRepository.delete(member);

        return member;
    }

    public Instrument getInstrumentById(Long id) {
        return instrumentRepository.findById(id).orElseThrow();
    }

    public Instrument createInstrument(Instrument instrument) {
        if (instrumentRepository.existsByISIN(instrument.getISIN())) throw new AlreadyExistsException(instrument);

        return instrumentRepository.save(instrument);
    }

    public Instrument updateInstrument(Instrument instrument) {
        Instrument existingInstrument = getInstrumentById(instrument.getId());

        existingInstrument.updateFields(instrument);

        return instrumentRepository.save(instrument);
    }

    public Instrument deleteInstrumentById(Long id) {
        Instrument instrument = getInstrumentById(id);

        instrumentRepository.delete(instrument);

        return instrument;
    }
}
