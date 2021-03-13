package com.kyeeego.TFood.exception_handlers;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private String url;
}