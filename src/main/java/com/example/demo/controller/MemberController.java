package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Member;
import com.example.demo.model.MemberDTO;
import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;

@RestController
@RequestMapping("members")
public class MemberController {
    
    private final VenueService venueService;

    public MemberController (VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("")
    public List<Member> getMembers() {
        return venueService.getMembers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-member")
    public Member addMember(@RequestBody MemberDTO memberDTO) {

        Venue venue = venueService.getVenueByName(memberDTO.getVenueName());

        if (venue == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The venue identified by the given name doesn't exist.");

        memberDTO.setVenue(venue);

        return venueService.saveMember(Member.of(memberDTO));
    }

    @PutMapping("/update-member")
    public Member updateMember(@RequestBody MemberDTO memberDTO) {

        if (venueService.getMemberByName(memberDTO.getLegalName()) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The member identified by the given legal name doesn't exist.");

        Venue venue = venueService.getVenueByName(memberDTO.getVenueName());

        if (venue == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The venue identified by the given name doesn't exist.");

        memberDTO.setVenue(venue);

        return venueService.saveMember(Member.of(memberDTO));
    }

    @DeleteMapping("/delete-member")
    public Member deleteMember(@RequestBody MemberDTO memberDTO) {
        Member member = venueService.getMemberByName(memberDTO.getLegalName());
        
        if (member == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The member identified by the given legal name doesn't exist.");

        venueService.deleteMemberByName(member.getLegalName());

        return member;
    }
}
