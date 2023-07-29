package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Customer extends Person {
    private int customerNumber;
    @OneToMany(mappedBy = "accountHolder", fetch = FetchType.LAZY)
    private List<Account> accountList;

    public Customer(){

    }

    public Customer(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Customer(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address, List<Account> accountList) {
        super(name, lastName, cardNumber, phoneNumber, email, address);
        this.accountList = accountList;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
