package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.dto.VenueDTO;
import com.example.demo.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public List<String> getVenues() {
        return venueService.getVenueNames();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Venue createVenue(@RequestBody VenueDTO venueDTO) {
        Venue venue = Venue.of(venueDTO);
        return venueService.createVenue(venue);
    }

    @GetMapping("/{id}")
    public Venue getVenue(@PathVariable long id) {
        return venueService.getVenueById(id);
    }

    @PutMapping(value = "/{id}", consumes="application/json")
    public Venue updateVenue(@RequestBody VenueDTO venueDTO, @PathVariable long id) {
        venueDTO.setId(id);
        return venueService.updateVenue(Venue.of(venueDTO));
    }

    @DeleteMapping("/{id}")
    public Venue deleteVenue(@PathVariable long id) {
        return venueService.deleteVenueById(id);
    }

}
