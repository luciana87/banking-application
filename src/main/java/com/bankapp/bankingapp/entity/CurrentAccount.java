package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "current_account")
public class CurrentAccount extends Account{

    private double overdraft;

    public CurrentAccount() {
    }

    @Override
    public String getType() {
        return "Current account";
    }

    public CurrentAccount(double overdraft) {
        this.overdraft = overdraft;
    }

    public CurrentAccount(String alias, Branch branch, Customer accountHolder, double overdraft) {
        super(alias, branch, accountHolder);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }



}
