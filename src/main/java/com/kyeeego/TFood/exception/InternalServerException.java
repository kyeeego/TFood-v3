package com.kyeeego.TFood.exception;

public class InternalServerException extends ApiException {

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException() {
        super("Unidentified internal server error");
    }
}
