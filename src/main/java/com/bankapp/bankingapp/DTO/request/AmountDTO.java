package com.bankapp.bankingapp.DTO.request;

import jakarta.validation.constraints.NotNull;

public class AmountDTO {
    @NotNull
    private double amount;
    public AmountDTO() {}

    public AmountDTO(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
