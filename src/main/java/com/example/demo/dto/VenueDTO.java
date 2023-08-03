package com.example.demo.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VenueDTO extends RepresentationModel<VenueDTO> {
    @NonNull
    private String name;

    private String city;

    private String country;

    private Long id;
}
