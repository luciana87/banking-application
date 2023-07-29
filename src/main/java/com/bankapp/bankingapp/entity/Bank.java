package com.bankapp.bankingapp.entity;

import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    protected String bankCode;

    protected String name;
    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    protected List<Branch> branchList;

    public Bank() {
    }

    public Bank(String bankCode, String name, List<Branch> branchList) {
        this.bankCode = bankCode;
        this.name = name;
        this.branchList = branchList;
    }

    public Integer getId() {
        return id;
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

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "bank_code" :
                this.bankCode = (String) newValue;
                break;
            case "name" :
                this.name = (String) newValue;
                break;
            default:
                throw new ResourceNotFoundException("No se permite modificar un campo solicitado");
        }
    }
}
