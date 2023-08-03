package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO extends RepresentationModel<MemberDTO> {
    private String lei;

    private String name;

    private String description;

    private String address;

    private String venueName;

    private Long id;
}
