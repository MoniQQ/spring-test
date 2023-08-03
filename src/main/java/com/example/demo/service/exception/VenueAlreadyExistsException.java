package com.example.demo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VenueAlreadyExistsException extends IllegalArgumentException {
    public VenueAlreadyExistsException() {
        super("Venue already exists.");
    }
}
