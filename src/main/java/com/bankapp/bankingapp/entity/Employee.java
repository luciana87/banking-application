package com.bankapp.bankingapp.entity;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Person {
    private int employeeNumber;

    public Employee(){

    }
    public Employee(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Employee(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address, int employeeNumber) {
        super(name, lastName, cardNumber, phoneNumber, email, address);
        this.employeeNumber = employeeNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
