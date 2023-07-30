package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.After;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.MemberController;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import com.example.demo.service.VenueService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerIntegrationTest {
    
    @Autowired
    private VenueService venueService;

    @Autowired
    private MemberController memberController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired 
    private JdbcTemplate jdbcTemplate;

 
    //@Transactional
    @BeforeEach
    public void dbSetup() {
        Venue venue = new Venue("Test venue", "Test city", "Test country");

        Member member1 = new Member(null, "LEI-1", "Rino", "desc", "address 1-a", venue);
        Member member2 = new Member(null, "LEI-2", "ff", "", "address-f-f", venue);

        venueService.saveVenue(venue);
        venueService.saveMember(member1);
        venueService.saveMember(member2);
        
        venueService.saveVenue(venue);

        // System.out.println(venueService.getVenueByName("Test venue").getMembers());

        // System.out.println(venueService.getVenueByName("Test venue").getMembers().stream().map(x -> x.getLegalName()).collect(Collectors.toList()));
    }

    @AfterEach
    public void dbCleanup() {
        //jdbcTemplate.execute("truncate table venue");
        jdbcTemplate.execute("truncate table member");
    }

    // @AfterEach
    // public void dbCleanup() {
    //     venueService.truncateMemberRepository();
    //     venueService.truncateVenueRepository();
    // }

    @Test 
    public void getVenueMembersTest() throws Exception {
        //assertEquals(venueService.getVenueByName("Test venue").getMembers().size(), 2);

        mockMvc.perform(get("/venues"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.containsInAnyOrder("Test venue")));
    }

   


}
