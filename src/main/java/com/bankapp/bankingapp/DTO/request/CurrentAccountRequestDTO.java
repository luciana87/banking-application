package com.bankapp.bankingapp.DTO.request;

import jakarta.validation.constraints.NotNull;

public class CurrentAccountRequestDTO extends AccountRequestDTO{
    @NotNull
    private double overdraft;

    public CurrentAccountRequestDTO (){

    }

    public CurrentAccountRequestDTO(String accountNumber, String alias, Integer branchId, CustomerRequestDTO customerRequestDTO, Integer employeeId, double overdraft) {
        super(accountNumber, alias, branchId, customerRequestDTO, employeeId);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}
