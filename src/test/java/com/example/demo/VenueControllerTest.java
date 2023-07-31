package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.service.exception.VenueNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.VenueController;
import com.example.demo.model.Venue;
import com.example.demo.dto.VenueDTO;
import com.example.demo.service.VenueService;
import com.fasterxml.jackson.databind.ObjectMapper;


class VenueControllerTest {
    @Mock
    private VenueService venueService;

    @InjectMocks
    private VenueController venueController;
   
    private MockMvc mockMvc;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(venueController).build();
    }

    @Test
    public void getVenueNamesTest() throws Exception {
        List<String> fakeList = Arrays.asList("Venue A", "Venue B");

        when(venueService.getVenueNames()).thenReturn(fakeList);

        mockMvc.perform(get("/venues"))
                .andExpect(status().isOk())
                //.andDo(print())
                .andExpect(jsonPath("$", Matchers.containsInAnyOrder("Venue A", "Venue B")));
    }

    @Test
    public void createVenueTest() throws Exception {
        Venue venue = new Venue(null, "Venue A", "City A", "Uzbekistan");
        VenueDTO dto = new VenueDTO(null, "Venue A", "City A", "Uzbekistan");

        when(venueService.createVenue(any(Venue.class))).thenReturn(venue);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        )
                .andExpect(status().is(201))
                //.andDo(print())
                .andExpect(jsonPath("$.name").value("Venue A"))
                .andExpect(jsonPath("$.city").value("City A"))
                .andExpect(jsonPath("$.country").value("Uzbekistan"));
    }


    @Test
    public void getVenueTest() throws Exception {
        Venue venue = new Venue(15L, "Venue A", "City A", "Uzbekistan");
        VenueDTO dto = new VenueDTO(15L, "Venue A", "City A", "Uzbekistan");

        when(venueService.getVenueById(eq(15L))).thenReturn(venue);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(get("/venues/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        )
                .andExpect(status().isOk())
                //.andDo(print())
                .andExpect(jsonPath("$.name").value("Venue A"))
                .andExpect(jsonPath("$.city").value("City A"))
                .andExpect(jsonPath("$.country").value("Uzbekistan"));
    }

    @Test
    public void updateVenueDetailsTest() throws Exception {
        Venue venue = new Venue(15L, "Venue A", "City A", "Uzbekistan");
        VenueDTO dto = new VenueDTO(15L, "Venue A", "City A", "Uzbekistan");

        when(venueService.updateVenue(any(Venue.class))).thenThrow(new VenueNotFoundException());

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/venues/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        )
                .andExpect(status().is(404));

        when(venueService.updateVenue(any(Venue.class))).thenReturn(venue);

        mockMvc.perform(put("/venues/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        )
                .andExpect(status().isOk())
                //.andDo(print())
                .andExpect(jsonPath("$.name").value("Venue A"))
                .andExpect(jsonPath("$.city").value("City A"))
                .andExpect(jsonPath("$.country").value("Uzbekistan"));
    }

}
