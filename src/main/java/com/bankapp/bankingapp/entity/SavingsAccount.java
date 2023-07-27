package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "savings_account")
public class SavingsAccount extends Account{

    public SavingsAccount() {
    }

    public SavingsAccount(double balance, String alias, Branch branch, Customer customer) {
        super(balance, alias, branch, customer);
    }

}
