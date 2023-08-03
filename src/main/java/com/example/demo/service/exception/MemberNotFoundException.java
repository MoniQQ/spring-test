package com.example.demo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
@ResponseBody
public class MemberNotFoundException extends ResponseStatusException {
    public MemberNotFoundException() {
        super(NOT_FOUND, "Member not found.");
    }
}
