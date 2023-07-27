package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "current_account")
public class CurrentAccount extends Account{

    private double overdraft;

    public CurrentAccount() {
    }

    public CurrentAccount(double overdraft) {
        this.overdraft = overdraft;
    }

    public CurrentAccount(double balance, String alias, Branch branch, Customer customer, double overdraft) {
        super(balance, alias, branch, customer);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}
