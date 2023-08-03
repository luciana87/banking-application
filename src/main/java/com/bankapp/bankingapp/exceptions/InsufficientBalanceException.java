package com.bankapp.bankingapp.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public static final String MESSAGE = "El monto a extraer es mayor al saldo disponible.";

    public InsufficientBalanceException() {
        super(MESSAGE);
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
