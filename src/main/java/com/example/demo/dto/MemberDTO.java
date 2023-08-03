package com.example.demo.dto;

import com.example.demo.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String lei;

    private String name;

    private String description;

    private String address;

    private String venueName;
}
