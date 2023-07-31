package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "savings_account")
public class SavingsAccount extends Account{

    public SavingsAccount() {
    }

    public SavingsAccount(String alias, Branch branch, Customer accountHolder) {
        super(alias, branch, accountHolder);
    }


    @Override
    public String getType() {
        return "Savings account";
    }
}
