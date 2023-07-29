package com.bankapp.bankingapp.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class BankResponseDTO {
    @NotEmpty
    private Integer id;
    @JsonProperty("bank_code")
    @NotEmpty
    private String bankCode;
    @NotEmpty
    private String name;

    public BankResponseDTO() {
    }

    public BankResponseDTO(Integer id, String bankCode, String name) {
        this.id = id;
        this.bankCode = bankCode;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


