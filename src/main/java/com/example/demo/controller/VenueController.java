package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.model.VenueDTO;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add-venue")
    public Venue addVenue(@RequestBody VenueDTO venueDTO){
        Venue venue = new Venue(venueDTO.getName(), venueDTO.getCity(), venueDTO.getCountry());
        return venueService.saveVenue(venue);
    }
}
