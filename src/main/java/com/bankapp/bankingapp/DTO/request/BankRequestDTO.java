package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class BankRequestDTO {

    @JsonProperty("bank_code")
    @NotEmpty
    private String bankCode;
    @NotEmpty
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
