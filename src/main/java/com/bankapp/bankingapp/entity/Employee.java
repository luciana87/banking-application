package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Employee extends Person {
    private int employeeNumber;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Employee(){

    }
    public Employee(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Employee(int employeeNumber, List<Account> accounts) {
        this.employeeNumber = employeeNumber;
        this.accounts = accounts;
    }

    public Employee(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address, int employeeNumber, List<Account> accounts) {
        super(name, lastName, cardNumber, phoneNumber, email, address);
        this.employeeNumber = employeeNumber;
        this.accounts = accounts;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
