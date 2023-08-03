package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public List<MemberDTO> getMembers() {
        return venueService.getMembers().stream().map(MemberController::addLinks).toList();
    }

    @GetMapping("/{id}")
    public MemberDTO getMember(@PathVariable Long id) {
        return addLinks(venueService.getMemberById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberDTO createMember(@RequestBody MemberDTO memberDTO) {
        Venue venue = venueService.getVenueByName(memberDTO.getVenueName());

        return addLinks(venueService.createMember(Member.of(null, memberDTO, venue)));
    }

    @PutMapping("/{id}")
    public MemberDTO updateMember(@RequestBody MemberDTO memberDTO, @PathVariable Long id) {
        Venue venue = venueService.getVenueByName(memberDTO.getVenueName());

        return addLinks(venueService.updateMember(Member.of(id, memberDTO, venue)));
    }

    @DeleteMapping("/{id}")
    public MemberDTO deleteMember(@PathVariable Long id) {
        return Member.dto(venueService.deleteMemberById(id));
    }

    static MemberDTO addLinks(Member member) {
        MemberDTO memberDTO = Member.dto(member);

        memberDTO.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).getMember(member.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VenueController.class).getVenue(member.getVenue().getId())).withRel("venue")
//                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).updateMember(memberDTO, member.getId())).withRel("update"),
//                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).deleteMember(member.getId())).withRel("delete")
        );

        return memberDTO;
    }
}
