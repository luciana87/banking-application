package com.bankapp.bankingapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bank {
    @Id
    protected String bankCode;
    protected String name;
    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    protected List<Branch> branchList;

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
