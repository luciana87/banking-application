package com.bankapp.bankingapp.DTO.response;

public class BankResponseDTO {
    private String name;

    public BankResponseDTO() {
    }

    public BankResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
