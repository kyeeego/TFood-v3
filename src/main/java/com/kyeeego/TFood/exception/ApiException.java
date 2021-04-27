package com.kyeeego.TFood.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class ApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ApiException(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    public ApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

