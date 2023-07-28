package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankRequestDTO {

    @JsonProperty("bank_code")
    private String bankCode;
    private String name;

    public BankRequestDTO() {
    }

    public BankRequestDTO(String bankCode, String name) {
        this.bankCode = bankCode;
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
}
