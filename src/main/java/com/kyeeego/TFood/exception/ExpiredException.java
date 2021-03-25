package com.kyeeego.TFood.exception;

public class ExpiredException extends RuntimeException {
    public ExpiredException() {
        super("Expired");
    }

    public ExpiredException(String message) {
        super(message);
    }
}
