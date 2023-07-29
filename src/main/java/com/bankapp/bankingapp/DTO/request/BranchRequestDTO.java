package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BranchRequestDTO {

    @JsonProperty("branch_code")
    @NotEmpty
    private String branchCode;
    @JsonProperty("address")
    @NotNull
    private AddressRequestDTO addressRequestDTO;
    @JsonProperty("bank_id")
    @NotNull
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
