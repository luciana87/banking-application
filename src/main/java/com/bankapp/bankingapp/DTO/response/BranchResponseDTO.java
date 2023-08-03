package com.bankapp.bankingapp.DTO.response;

import com.bankapp.bankingapp.DTO.request.AddressRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BranchResponseDTO {
    @NotNull
    private Integer id;
    @JsonProperty("branch_code")
    @NotEmpty
    private String branchCode;
    @JsonProperty("address")
    @NotNull
    private AddressResponseDTO addressResponseDTO;
    @JsonProperty("bank_code")
    @NotNull
    private String bank;
//    @JsonProperty("account_list")
//    @NotNull
//    protected List<Account> accountList;

    public BranchResponseDTO(){}
    public BranchResponseDTO(Integer id, String branchCode, AddressResponseDTO addressResponseDTO, String bank) {
        this.id = id;
        this.branchCode = branchCode;
        this.addressResponseDTO = addressResponseDTO;
        this.bank = bank;
//        this.accountList = accountList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public AddressResponseDTO getAddressResponseDTO() {
        return addressResponseDTO;
    }

    public void setAddressResponseDTO(AddressResponseDTO addressResponseDTO) {
        this.addressResponseDTO = addressResponseDTO;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

//    public List<Account> getAccountList() {
//        return accountList;
//    }
//
//    public void setAccountList(List<Account> accountList) {
//        this.accountList = accountList;
//    }
}
