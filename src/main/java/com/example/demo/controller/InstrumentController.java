package com.example.demo.controller;

import com.example.demo.service.VenueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("instruments")
public class InstrumentController {
    private final VenueService venueService;

    public InstrumentController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public
}
