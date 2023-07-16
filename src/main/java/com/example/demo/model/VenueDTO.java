package com.example.demo.model;

public class VenueDTO {

    public VenueDTO(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;
}
