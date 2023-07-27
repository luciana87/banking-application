package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Customer extends Person {
    private int customerNumber;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CurrentAccount> currentAccountList;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<SavingsAccount> savingsAccountList;
    public Customer(){

    }

    public Customer(List<CurrentAccount> currentAccountList, List<SavingsAccount> savingsAccountList) {
        this.currentAccountList = currentAccountList;
        this.savingsAccountList = savingsAccountList;
    }

    public Customer(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address, List<CurrentAccount> currentAccountList, List<SavingsAccount> savingsAccountList) {
        super(name, lastName, cardNumber, phoneNumber, email, address);
        this.currentAccountList = currentAccountList;
        this.savingsAccountList = savingsAccountList;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }

    public void setCurrentAccountList(List<CurrentAccount> currentAccountList) {
        this.currentAccountList = currentAccountList;
    }

    public List<SavingsAccount> getSavingsAccountList() {
        return savingsAccountList;
    }

    public void setSavingsAccountList(List<SavingsAccount> savingsAccountList) {
        this.savingsAccountList = savingsAccountList;
    }
}
