package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.VenueController;
import com.example.demo.service.VenueService;


@SpringBootTest
class VenueControllerTest {
 
    private final VenueService venueService = mock(VenueService.class);

    private final VenueController venueController = new VenueController(venueService);
   


    @BeforeEach
    public void init() {
        System.out.println("test test");
    }

    @Test
    public void getVenueNamesTest() {
        when(venueService.getVenueNames()).thenReturn(List.of("Epic venue"));
        assertEquals(venueController.getVenues(), List.of("Epic venue"));
    }

}
