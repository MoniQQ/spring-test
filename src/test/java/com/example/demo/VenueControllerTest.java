package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.controller.VenueController;
import com.example.demo.service.VenueService;



@SpringBootTest
@AutoConfigureMockMvc
class VenueControllerTest {
 
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Mock
    private VenueService venueService;

    @Autowired
    private VenueController venueController;
   
    @Autowired 
    private MockMvc mockMvc;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        System.out.println("test test");
    }

    @Test
    public void getVenueNamesTest() throws Exception {
        List<String> expected = List.of("Epic venue");

        when(venueService.getVenueNames()).thenReturn(expected);

        mockMvc.perform(get("/venues"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").value(expected.toArray()));

        assertEquals(venueController.getVenues(), List.of("Epic venue"));
    }

}
