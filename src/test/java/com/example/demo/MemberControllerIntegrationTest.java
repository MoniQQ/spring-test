package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.VenueDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.After;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
    private MockMvc mockMvc;

    @Autowired 
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void dbSetup() {
        Venue venue = new Venue("Test venue", "Test city", "Test country");

        Member member1 = new Member(null, "LEI-1", "Rino", "desc", "address 1-a", venue);
        Member member2 = new Member(null, "LEI-2", "ff", "", "address-f-f", venue);

        venueService.createVenue(venue);
        venueService.createMember(member1);
        venueService.createMember(member2);
    }

    @AfterEach
    public void dbCleanup() {
        //jdbcTemplate.batchUpdate()
        jdbcTemplate.execute("set REFERENTIAL_INTEGRITY false");
        jdbcTemplate.execute("truncate table MEMBERS");
        jdbcTemplate.execute("truncate table VENUES");
        jdbcTemplate.execute("truncate table INSTRUMENTS");
        jdbcTemplate.execute("truncate table ISSUERS");
        jdbcTemplate.execute("set REFERENTIAL_INTEGRITY true");
    }


    @Test 
    public void getVenueMembersTest() throws Exception {
        mockMvc.perform(get("/venues"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.contains("Test venue")));
    }

    @Test
    public void addVenueWithMembersTest() throws Exception {
        VenueDTO venueDTO = new VenueDTO("My venue", "My city", "My country", null);

        MvcResult result1 = mockMvc.perform(
                    post("/venues")
                            .content(objectMapper.writeValueAsString(venueDTO))
                            .contentType(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("My venue"))
                .andExpect(jsonPath("$.city").value("My city"))
                .andExpect(jsonPath("$.country").value("My country"))
                .andReturn();

        Venue createdVenue = objectMapper.readValue(result1.getResponse().getContentAsString(), Venue.class);

        Long id = createdVenue.getId();

        MemberDTO memberDTO = new MemberDTO("LEI-0000", "Scrooge McDuck", "", "", "My venue");

        MvcResult result2 = mockMvc.perform(
                post("/members")
                        .content(objectMapper.writeValueAsString(memberDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lei").value("LEI-0000"))
                .andExpect(jsonPath("$.name").value("Scrooge McDuck"))
                .andExpect(jsonPath("$.venue.name").value("My venue"))
                .andReturn();
    }
}
