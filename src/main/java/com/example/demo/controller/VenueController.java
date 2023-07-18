package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.model.VenueDTO;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {
    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping(value = "",  produces="application/json")
    public List<String> getVenues() {
        return venueService.getVenueNames();
    }

    @ResponseStatus( HttpStatus.CREATED )
    @PostMapping("/create-venue")
    public Venue createVenue(@RequestBody VenueDTO venueDTO) {
        Venue venue = new Venue(venueDTO.getId(), venueDTO.getName(), venueDTO.getCity(), venueDTO.getCountry());
        Venue savedVenue = venueService.saveVenue(venue);
        return savedVenue;
    }

    @GetMapping("/get-venue")
    public Venue getVenue(@RequestBody VenueDTO venueDTO) {
        Venue venue = venueService.getVenueByName(venueDTO.getName());
        if (venue == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The venue identified by the given name doesn't exist.");
        return venue;
    }

    @PutMapping(value = "/update-venue-details", consumes="application/json")
    public Venue updateVenueDetails(@RequestBody VenueDTO venueDTO) {
        Venue updatedVenue = venueService.updateVenueDetails(new Venue(venueDTO.getId(), venueDTO.getName(), venueDTO.getCity(), venueDTO.getCity()));
        if (updatedVenue == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The venue identified by the given ID doesn't exist.");
        return updatedVenue;
    }

}
