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
    @DisplayName("Extraigo mismo monto que el saldo positivo de cuenta corriente")
    void testDepositar(){

        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(1000);
        double amount = 1000;

        savingsAccount.deposit(amount);

        assertEquals(savingsAccount.getBalance(), 2000);
    }


}