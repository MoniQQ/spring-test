package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.model.MemberDTO;
import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
