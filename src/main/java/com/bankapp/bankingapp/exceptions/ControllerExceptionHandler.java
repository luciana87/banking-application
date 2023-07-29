package com.bankapp.bankingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    //Excepción para recursos vacíos
    @ExceptionHandler (ExistingResourceException.class)
    public ResponseEntity handleException(ExistingResourceException e) {

        return new ResponseEntity(ExistingResourceException.MESSAGE, HttpStatus.CONFLICT);
    }

    //Excepción para recursos no encontrados
    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException e) {

        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Excepción lanzada por las validaciones con spring
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
