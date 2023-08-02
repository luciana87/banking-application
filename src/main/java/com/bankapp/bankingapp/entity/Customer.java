package com.bankapp.bankingapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends Person {
    @Column(unique = true)
    private int customerNumber;
    @OneToMany(mappedBy = "accountHolder", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Account> accountList;

    public Customer(){

    }

    public Customer(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Customer(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address, List<Account> accountList) {
        super(name, lastName, cardNumber, phoneNumber, email, address);
        this.accountList = new ArrayList<Account>();
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

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "name":
                this.name = (String) newValue;
                break;
            case "last_name":
                this.lastName = (String) newValue;
                break;
            case "card_number":
                this.cardNumber = (String) newValue;
                break;
            case "phone_number":
                this.phoneNumber = ((Integer) newValue).longValue();
                break;
            case "email":
                this.email = (String) newValue;
                break;
            case "customer_number":
                this.customerNumber = (int) newValue;
                break;
        }
    }
}
