package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BranchRequestDTO {

    @JsonProperty("branch_code")
    @NotEmpty (message = "Branch code may not be null or empty")
    @Size(min = 4, max = 4)
    private String branchCode;
    @JsonProperty("address")
    @NotNull (message = "Address may not be null or empty")
    private AddressRequestDTO addressRequestDTO;
    @JsonProperty("bank_id")
    @NotNull (message = "Bank may not be null or empty")
    private Integer bank;

    public BranchRequestDTO (){

    }

    public BranchRequestDTO(String branchCode, AddressRequestDTO addressRequestDTO, Integer bank) {
        this.branchCode = branchCode;
        this.addressRequestDTO = addressRequestDTO;
        this.bank = bank;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public AddressRequestDTO getAddressRequestDTO() {
        return addressRequestDTO;
    }

    public void setAddressRequestDTO(AddressRequestDTO address) {
        this.addressRequestDTO = address;
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }
}
