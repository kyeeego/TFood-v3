package com.kyeeego.TFood.exceptionhandlers;

import com.kyeeego.TFood.domain.exception.user.UserAlreadyExistsException;
import com.kyeeego.TFood.domain.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("No record with such id");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return errorResponse;

    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("User with such ID already exists");
        errorResponse.setStatus(HttpStatus.CONFLICT);
        return errorResponse;

    }

}
