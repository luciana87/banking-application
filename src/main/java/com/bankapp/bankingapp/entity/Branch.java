package com.bankapp.bankingapp.entity;

import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Branch{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "code")
    private String branchCode;
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //Una sucursal tiene una dirección
    @JoinColumn (name = "address_id") //Foreign Key: address_id
    private Address address;
    @ManyToOne
    @JoinColumn (name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    protected List<Account> accountList;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    protected List<Employee> employeeList;


    public Branch() {
    }

    public Branch(String branchCode, Address address, Bank bank, List<Account> accountList, List<Employee> employeeList) {
        this.branchCode = branchCode;
        this.address = address;
        this.bank = bank;
        this.accountList = accountList;
        this.employeeList = employeeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
