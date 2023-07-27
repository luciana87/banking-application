package com.bankapp.bankingapp.entity;

import jakarta.persistence.*;


@MappedSuperclass
public class Account {
    @Id
    protected String accountNumber;
    protected double balance;
    protected String alias;
    @ManyToOne
    @JoinColumn (name= "branch_id")
    protected Branch branch;

    @ManyToOne
    @JoinColumn (name= "customer_id")
    protected Customer customer;


    public Account() {
    }

    public Account(double balance, String alias, Branch branch, Customer customer) {
        this.balance = balance;
        this.alias = alias;
        this.branch = branch;
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
