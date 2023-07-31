package com.bankapp.bankingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    //Excepción para recursos vacíos
    @ExceptionHandler (ExistingResourceException.class)
    public ResponseEntity handleException(ExistingResourceException e) {

        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
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

    // Valida que no lleguen recursos vacíos
    @ExceptionHandler (EmptyResourceException.class)
    public ResponseEntity handleException(EmptyResourceException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //Excepción lanzada por las validaciones con spring para campos duplicados
    @ExceptionHandler (SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleException(SQLIntegrityConstraintViolationException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
