package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Customer extends Person {
    private int customerNumber;
    @OneToMany(mappedBy = "accountHolder", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Customer(){

    }

    public Customer(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Customer(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address, List<Account> accounts) {
        super(name, lastName, cardNumber, phoneNumber, email, address);
        this.accounts = accounts;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
