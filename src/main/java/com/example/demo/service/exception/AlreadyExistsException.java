package com.example.demo.service.exception;

import com.example.demo.model.Instrument;
import com.example.demo.model.Issuer;
import com.example.demo.model.Member;
import com.example.demo.model.Venue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(HttpStatus.CONFLICT)
@ResponseBody
public class AlreadyExistsException extends ResponseStatusException {
    public AlreadyExistsException(Member member) {
        super(CONFLICT, "Member " + member.getLei() + " already exists.");
    }

    public AlreadyExistsException(Instrument instrument) {
        super(CONFLICT, "Instrument " + instrument.getISIN() + " already exists.");
    }

    public AlreadyExistsException(Venue venue) {
        super(CONFLICT, "Venue " + venue.getName() + " already exists.");
    }

    public AlreadyExistsException(Issuer issuer) {
        super(CONFLICT, "Issuer " + issuer.getName() + " already exists.");
    }
}
