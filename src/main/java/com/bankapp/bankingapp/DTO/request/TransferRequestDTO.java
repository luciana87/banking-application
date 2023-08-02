package com.bankapp.bankingapp.DTO.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TransferRequestDTO {
    @NotNull
    private double amount;
    @NotEmpty
    private String cbu;

    public TransferRequestDTO(double amount, String cbu) {
        this.amount = amount;
        this.cbu = cbu;
    }
    public TransferRequestDTO() {
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }
}
