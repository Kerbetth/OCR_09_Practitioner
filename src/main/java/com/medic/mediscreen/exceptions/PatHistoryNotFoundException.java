package com.medic.mediscreen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatHistoryNotFoundException extends RuntimeException {


    public PatHistoryNotFoundException(String message) {
        super(message);
    }
}