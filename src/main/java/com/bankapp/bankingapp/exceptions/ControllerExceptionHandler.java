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
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    // Valida que no lleguen recursos vacíos
    @ExceptionHandler (EmptyResourceException.class)
    public ResponseEntity handleException(EmptyResourceException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //Excepción lanzada por las validaciones con spring para campos duplicados
    @ExceptionHandler (SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleException(SQLIntegrityConstraintViolationException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler (InsufficientBalanceException.class)
    public ResponseEntity handleException(InsufficientBalanceException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handlValidationException(MethodArgumentNotValidException ex) {
//        // Obtener el primer error de validación
//        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
//        // Devolver una respuesta con código 400 y el mensaje de error
//        return ResponseEntity.badRequest().body(errorMessage);
//    }

}
