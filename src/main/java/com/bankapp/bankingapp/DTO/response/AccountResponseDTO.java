package com.bankapp.bankingapp.DTO.response;

import com.bankapp.bankingapp.DTO.request.CustomerRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AccountResponseDTO {

    @NotEmpty
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotEmpty
    protected String accountNumber;
    @NotEmpty
    protected String alias;
    @NotNull
    protected CustomerResponseDTO customerResponseDTO;

    public AccountResponseDTO () {}
    public AccountResponseDTO(String accountNumber, String alias, CustomerResponseDTO customerResponseDTO) {
        this.accountNumber = accountNumber;
        this.alias = alias;
        this.customerResponseDTO = customerResponseDTO;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public CustomerResponseDTO getCustomerResponseDTO() {
        return customerResponseDTO;
    }

    public void setCustomerResponseDTO(CustomerResponseDTO customerResponseDTO) {
        this.customerResponseDTO = customerResponseDTO;
    }
}

