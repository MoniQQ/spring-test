package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {
    
    private final VenueService venueService;

    public MemberController (VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public List<Member> getMembers() {
        return venueService.getMembers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Member createMember(@RequestBody MemberDTO memberDTO) {
        Venue venue = venueService.getVenueByName(memberDTO.getVenueName());

        return venueService.createMember(Member.of(null, memberDTO, venue));
    }

    @PutMapping("/{id}")
    public Member updateMember(@RequestBody MemberDTO memberDTO, @PathVariable Long id) {
        Venue venue = venueService.getVenueByName(memberDTO.getVenueName());

        return venueService.updateMember(Member.of(id, memberDTO, venue));
    }

    @DeleteMapping("/{id}")
    public Member deleteMember(@PathVariable Long id) {
        return venueService.deleteMemberById(id);
    }
}
