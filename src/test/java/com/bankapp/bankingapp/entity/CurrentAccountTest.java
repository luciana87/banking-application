package com.bankapp.bankingapp.entity;

import com.bankapp.bankingapp.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class) // Librerías JUnit. Le digo que esta clase va a ser una clase de test unitarios
class CurrentAccountTest {
    @Test
    @DisplayName("Extraer monto de cuenta corriente ok")
    void testExtraerMontoPositivo(){

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(1000);
        double amount = 200;

        currentAccount.withdraw(amount);

        assertEquals(currentAccount.getBalance(), 800);
    }

    @Test
    @DisplayName("Extraer monto mayor al saldo de cuenta corriente lanza excepción")
    void testExtraerMontoMayorAlSaldo(){

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(1000);
        currentAccount.setOverdraft(1000);
        double amount = 3000;


        assertThrows(InsufficientBalanceException.class, () -> currentAccount.withdraw(amount));
    }

    @Test
    @DisplayName("Extraigo monto menor al saldo de cuenta + descubierto de cuenta corriente, permite saldo negativo (overdraft)")
    void testExtraerMontoMenorAlSaldoMasDescubierto(){

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(1000);
        currentAccount.setOverdraft(1000);
        double amount = 1500;

        currentAccount.withdraw(amount);

        assertEquals(currentAccount.getBalance(), -500); //Permite saldo negativo: overdraft
    }

    @Test
    @DisplayName("Extraigo monto menor al saldo de cuenta negativo + descubierto de cuenta corriente, permite saldo negativo (overdraft)")
    void testExtraerMontoMenorAlSaldoNegativoMasDescubierto(){

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(-500);
        currentAccount.setOverdraft(1000);
        double amount = 200;

        currentAccount.withdraw(amount);

        assertEquals(currentAccount.getBalance(), -700); //Permite extraer a pesar de tener saldo negativo siempre y cuando no supere el overdraft
    }

    @Test
    @DisplayName("Extraigo mismo monto que el saldo positivo de cuenta corriente")
    void testExtraerMismoMontoAlSaldoPositivo(){

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(1000);
        double amount = 1000;

        currentAccount.withdraw(amount);

        assertEquals(currentAccount.getBalance(), 0);
    }

    @Test
    @DisplayName("Extraigo mismo monto que el saldo positivo de cuenta corriente")
    void testDepositar(){

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(1000);
        double amount = 1000;

        currentAccount.toDeposit(amount);

        assertEquals(currentAccount.getBalance(), 2000);
    }

}