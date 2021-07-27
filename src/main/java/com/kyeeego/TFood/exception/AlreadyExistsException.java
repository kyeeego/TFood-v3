package com.kyeeego.TFood.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends ApiException {
    public AlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Conflict with existing data");
    }

    public AlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
