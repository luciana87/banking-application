package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "savings_account")
public class SavingsAccount extends Account{

    public SavingsAccount() {
    }

    public SavingsAccount(String alias, Branch branch, Customer accountHolder, String cbu) {
        super(alias, branch, accountHolder, cbu);
    }

    @Override
    public String getType() {
        return "Savings account";
    }

    @Override
    public boolean checkAvailableBalance(double amount) {
        return amount <= balance;
    }
}
