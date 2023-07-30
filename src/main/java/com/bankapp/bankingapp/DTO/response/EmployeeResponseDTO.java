package com.bankapp.bankingapp.DTO.response;

import com.bankapp.bankingapp.DTO.request.AddressRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeResponseDTO {

    @NotNull
    protected   Integer id;
    @NotEmpty
    protected String name;
    @JsonProperty("last_name")
    @NotEmpty
    protected String lastName;
    @JsonProperty("card_number")
    @NotEmpty
    protected String cardNumber;
    @JsonProperty("phone_number")
    @NotNull
    protected long phoneNumber;
    @NotEmpty
    protected String email;
    @JsonProperty("address")
    @NotNull
    protected AddressResponseDTO addressReponseDTO;
    @JsonProperty("branch_id")
    @NotNull
    private Integer branch;
    @JsonProperty("employee_number")
    @NotNull
    private int employeeNumber;

    public EmployeeResponseDTO(){}
    public EmployeeResponseDTO(Integer id, String name, String lastName, String cardNumber, long phoneNumber,
                               String email, AddressResponseDTO addressReponseDTO, Integer branch, int employeeNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressReponseDTO = addressReponseDTO;
        this.branch = branch;
        this.employeeNumber = employeeNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressResponseDTO getAddressReponseDTO() {
        return addressReponseDTO;
    }

    public void setAddressReponseDTO(AddressResponseDTO addressReponseDTO) {
        this.addressReponseDTO = addressReponseDTO;
    }

    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
