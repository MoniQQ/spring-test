package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class VenueDTO {
    
    private Long id;
    private String name;
    private String city;
    private String country;    
}
