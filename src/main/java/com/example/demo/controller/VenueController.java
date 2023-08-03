package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.dto.VenueDTO;
import com.example.demo.service.VenueService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public List<VenueDTO> getVenues() {
        return venueService.getVenues().stream().map(this::addLinks).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VenueDTO createVenue(@RequestBody VenueDTO venueDTO) {
        Venue venue = venueService.createVenue(Venue.of(venueDTO));

        return addLinks(venue);
    }

    @GetMapping("/{id}")
    public VenueDTO getVenue(@PathVariable long id) {
        Venue venue = venueService.getVenueById(id);

        return addLinks(venue);
    }

    @PutMapping(value = "/{id}", consumes="application/json")
    public VenueDTO updateVenue(@RequestBody VenueDTO venueDTO, @PathVariable long id) {
        Venue venue = Venue.of(id, venueDTO);

        return addLinks(venue);
    }

    @DeleteMapping("/{id}")
    public VenueDTO deleteVenue(@PathVariable long id) {
        Venue venue = venueService.deleteVenueById(id);

        return Venue.dto(venue);
    }


    private VenueDTO addLinks(Venue venue) {
        VenueDTO venueDTO = Venue.dto(venue);

        venueDTO.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VenueController.class).getVenue(venue.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VenueController.class).updateVenue(venueDTO, venue.getId())).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VenueController.class).deleteVenue(venue.getId())).withRel("delete"));

        return venueDTO;
    }
}
