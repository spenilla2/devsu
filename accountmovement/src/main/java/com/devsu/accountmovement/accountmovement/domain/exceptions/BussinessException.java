package com.devsu.accountmovement.accountmovement.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class BussinessException extends RuntimeException{
    public BussinessException(String message) {
        super(message);
    }
}
