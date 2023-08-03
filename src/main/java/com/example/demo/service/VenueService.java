package com.example.demo.service;

import com.example.demo.model.Instrument;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import com.example.demo.repository.InstrumentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.VenueRepository;
import com.example.demo.service.exception.MemberAlreadyExistsException;
import com.example.demo.service.exception.MemberNotFoundException;
import com.example.demo.service.exception.VenueAlreadyExistsException;
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
        if (venueRepository.existsByName(venue.getName())) throw new VenueAlreadyExistsException();

        return venueRepository.save(venue);
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElseThrow(VenueNotFoundException::new);
    }

    public Venue getVenueByName(String name) {
        return venueRepository.findByName(name).orElseThrow(VenueNotFoundException::new);
    }

    public Venue updateVenue(Venue venue) {
        return venueRepository.save(
                getVenueById(venue.getId())
        );
    }
 
    public Venue deleteVenueById(Long id) {
        Venue venue = getVenueById(id);
        venueRepository.delete(venue);

        return venue;
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberByName(String name) {
        return memberRepository.findByname(name).orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public Member createMember(Member member) {
        if (member.getId() != null) throw new MemberAlreadyExistsException();

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

    public Member deleteMemberById(Long id) {
        Member member = getMemberById(id);

        memberRepository.delete(member);

        return member;
    }

    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public Instrument updateInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public void deleteInstrument(Instrument instrument) {
        instrumentRepository.delete(instrument);
    }
}
