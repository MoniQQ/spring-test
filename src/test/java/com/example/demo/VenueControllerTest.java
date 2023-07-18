package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.VenueController;
import com.example.demo.model.Venue;
import com.example.demo.model.VenueDTO;
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
        List<String> fakeList = List.of("Venue A", "Venue B");

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

        when(venueService.saveVenue(any(Venue.class))).thenReturn(venue);

        ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.writeValueAsString(dto));

        mockMvc.perform(post("/venues/create-venue")
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
        Venue venue = new Venue(null, "Venue A", "City A", "Uzbekistan");
        VenueDTO dto = new VenueDTO(null, "Venue A", "City A", "Uzbekistan");

        when(venueService.getVenueByName(eq("Venue A"))).thenReturn(venue);

        ObjectMapper mapper = new ObjectMapper();


        mockMvc.perform(get("/venues/get-venue")
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
        Venue venue = new Venue(null, "Venue A", "City A", "Uzbekistan");
        VenueDTO dto = new VenueDTO(null, "Venue A", "City A", "Uzbekistan");

        when(venueService.updateVenueDetails(any(Venue.class))).thenReturn(null);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/venues/update-venue-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .characterEncoding("utf-8")
                        )
                .andExpect(status().is(404));

        when(venueService.updateVenueDetails(any(Venue.class))).thenReturn(venue);

        mockMvc.perform(put("/venues/update-venue-details")
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
