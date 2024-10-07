package com.gitgudgang.fakeperson.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
public class EndpointNotFoundException extends RuntimeException {

    public EndpointNotFoundException() {
    }

    public EndpointNotFoundException(String message) {
        super(message);
    }

    public EndpointNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EndpointNotFoundException(Throwable cause) {
        super(cause);
    }
}
