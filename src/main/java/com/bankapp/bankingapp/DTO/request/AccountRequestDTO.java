package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AccountRequestDTO {
    @JsonProperty("account_number")
    @NotEmpty
    @Size(min = 10, max = 10)
    protected String accountNumber;
    @NotEmpty
    protected String alias;
    @JsonProperty("branch_id")
    @NotNull
    protected Integer branchId;

    @JsonProperty("account_holder")
    @NotNull
    protected CustomerRequestDTO customerRequestDTO;
    @JsonProperty("employee_id")
    @NotNull
    protected Integer employeeId;

    public AccountRequestDTO () {

    }

    public AccountRequestDTO(String accountNumber, String alias, Integer branchId, CustomerRequestDTO customerRequestDTO, Integer employeeId) {
        this.accountNumber = accountNumber;
        this.alias = alias;
        this.branchId = branchId;
        this.customerRequestDTO = customerRequestDTO;
        this.employeeId = employeeId;
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

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public CustomerRequestDTO getCustomerRequestDTO() {
        return customerRequestDTO;
    }

    public void setCustomerRequestDTO(CustomerRequestDTO customerRequestDTO) {
        this.customerRequestDTO = customerRequestDTO;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
