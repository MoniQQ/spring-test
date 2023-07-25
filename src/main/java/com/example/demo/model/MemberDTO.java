package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;

    private String lei;

    private String legalName;

    private String description;

    private String address;

    private String venueName;
    
    private Venue venue;
}
