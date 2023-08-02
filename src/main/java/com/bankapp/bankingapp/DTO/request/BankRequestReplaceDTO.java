package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class BankRequestReplaceDTO {
    @JsonProperty("bank_code")
    @NotEmpty (message = "Bank code may not be null or empty")
    private String bankCode;
    @NotEmpty (message = "Name may not be null or empty")
    private String name;

    public BankRequestReplaceDTO() {
    }

    public BankRequestReplaceDTO (String bankCode, String name) {
        this.bankCode = bankCode;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
