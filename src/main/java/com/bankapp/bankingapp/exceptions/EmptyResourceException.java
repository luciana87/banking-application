package com.bankapp.bankingapp.exceptions;

public class EmptyResourceException extends RuntimeException{
    public static final String MESSAGE = "No se permiten recursos vacíos.";

    public EmptyResourceException() {
        super(MESSAGE);
    }

    public EmptyResourceException(String message) {
        super(message);
    }
}
