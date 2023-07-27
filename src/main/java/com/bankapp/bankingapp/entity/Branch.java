package com.bankapp.bankingapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Branch{
    @Id
    @Column(name = "code")
    private String branchCode;
    @OneToOne //Una sucursal tiene una dirección
    @JoinColumn (name = "address_id") //Foreign Key: address_id
    private Address address;
    @ManyToOne
    @JoinColumn (name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    protected List<CurrentAccount> currentAccountList;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    protected List<SavingsAccount> savingsAccountList;

    public Branch() {
    }

    public Branch(Address address, List<CurrentAccount> currentAccountList, List<SavingsAccount> savingsAccountList) {
        this.address = address;
        this.currentAccountList = currentAccountList;
        this.savingsAccountList = savingsAccountList;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
