package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.MemberController;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;


@SpringBootTest
public class MemberControllerIntegrationTest {
    
    @Autowired
    private VenueService venueService;

    @Autowired
    private MemberController memberController;

    private MockMvc mockMvc;

 
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        Venue venue = new Venue("Test venue", "Test city", "Test country");

        Member rino = new Member(null, "LEI-1", "Rino", "desc", "address 1-a", venue);
        Member ff = new Member(null, "LEI-2", "ff", "", "address-f-f", venue);

        venueService.saveMember(rino);
        venueService.saveMember(ff);

        venue.setMembers(Arrays.asList(ff, rino));
        venueService.saveVenue(venue);

        System.out.println(venueService.getVenueByName("Test venue").getMembers());

        System.out.println(venueService.getVenueByName("Test venue").getMembers().stream().map(x -> x.getLegalName()).collect(Collectors.toList()));
    }

    // @Test 
    // // @Transactional
    // public void getVenuesTest() throws Exception {
    //     init();
    //     mockMvc.perform(get("/venues"))
    //             .andExpect(status().isOk())
    //             //.andDo(print())
    //             .andExpect(jsonPath("$", Matchers.containsInAnyOrder("Test venue")));
    // }

    @Test 
  //  @Transactional
    public void getVenueMembersTest() {
        init();
        assertEquals(venueService.getVenueByName("Test venue").getMembers().size(), 2);

    }



}
