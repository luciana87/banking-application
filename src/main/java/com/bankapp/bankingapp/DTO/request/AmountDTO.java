package com.bankapp.bankingapp.DTO.request;

import jakarta.validation.constraints.NotNull;

public class AmountDTO {
    @NotNull (message = "Amount may not be null or empty")
    private Double amount;
    public AmountDTO() {}

    public AmountDTO(Double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
