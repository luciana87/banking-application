package com.bankapp.bankingapp.entity;

import com.bankapp.bankingapp.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SavingsAccountTest {


    @Test
    @DisplayName("Extraer monto de caja de ahorro ok")
    void testExtraerMontoPositivo(){

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(1000);
        double amount = 200;

        savingsAccount.withdraw(amount);

        assertEquals(savingsAccount.getBalance(), 800);
    }

    @Test
    @DisplayName("Extraer monto mayor al saldo de caja de ahorro lanza excepción")
    void testExtraerMontoMayorAlSaldo(){

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(1000);
        double amount = 2000;

        assertThrows(InsufficientBalanceException.class, () -> savingsAccount.withdraw(amount));
    }

    @Test
    @DisplayName("Extraigo mismo monto que el saldo positivo de caja de ahorro")
    void testExtraerMismoMontoAlSaldo(){

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(1000);
        double amount = 1000;

        currentAccount.withdraw(amount);

        assertEquals(currentAccount.getBalance(), 0);
    }

    @Test
    @DisplayName("Extraigo mismo monto que el saldo positivo de caja de ahorro")
    void testDepositar(){

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(1000);
        double amount = 1000;

        savingsAccount.deposit(amount);

        assertEquals(savingsAccount.getBalance(), 2000);
    }

    @Test
    @DisplayName("Transfeir monto cuenta origen hacia cuenta destino ok, caja de ahorro")
    void testTransferirDeCuentaOrigenACuentaDestino(){

        SavingsAccount fromAccount = new SavingsAccount();
        fromAccount.setBalance(1000);

        SavingsAccount toAccount = new SavingsAccount();
        toAccount.setBalance(100);

        double amount = 500;

        fromAccount.transfer(amount, toAccount);

        assertEquals(toAccount.getBalance(), 600);
        assertEquals(fromAccount.getBalance(), 500);
    }

    @Test
    @DisplayName("Transfeir monto cuenta origen sin saldo hacia cuenta destino lanza excepción, caja de ahorro")
    void testTransferirDeCuentaOrigenSinSaldoACuentaDestino(){

        SavingsAccount fromAccount = new SavingsAccount();
        fromAccount.setBalance(100);

        SavingsAccount toAccount = new SavingsAccount();
        toAccount.setBalance(100);

        double amount = 500;

        assertThrows(InsufficientBalanceException.class, () -> fromAccount.transfer(amount, toAccount));
    }

}