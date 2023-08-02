package com.bankapp.bankingapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee extends Person {
    @Column (unique = true)
    private int employeeNumber;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Employee(){

    }

    public Employee(int employeeNumber, Branch branch, List<Account> accounts) {
        this.employeeNumber = employeeNumber;
        this.branch = branch;
        this.accounts = accounts;
    }

    public Employee(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address, int employeeNumber, Branch branch, List<Account> accounts) {
        super(name, lastName, cardNumber, phoneNumber, email, address);
        this.employeeNumber = employeeNumber;
        this.branch = branch;
        this.accounts = accounts;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "name" :
                this.name = (String) newValue;
                break;
            case "last_name" :
                this.lastName = (String) newValue;
                break;
            case "card_number" :
                this.cardNumber = (String) newValue;
                break;
            case "phone_number" :
                this.phoneNumber = ((Integer) newValue).longValue();
                break;
            case "email" :
                this.email = (String) newValue;
                break;
            case "employee_number" :
                this.employeeNumber = (int) newValue;
                break;
        }
    }
}
