package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequestDTO {

    @NotEmpty (message = "Name may not be null or empty")
    protected String name;
    @JsonProperty("last_name")
    @NotEmpty (message = "Last name may not be null or empty")
    protected String lastName;
    @JsonProperty("card_number")
    @NotEmpty (message = "Card number may not be null or empty")
    protected String cardNumber;
    @JsonProperty("phone_number")
    @NotNull (message = "Phone number may not be null or empty")
    protected long phoneNumber;
    @NotEmpty (message = "Email may not be null or empty")
    @Email (message = "Email should be valid")
    protected String email;
    @JsonProperty("address")
    @NotNull (message = "Address may not be null or empty")
    protected AddressRequestDTO addressRequestDTO;
    @JsonProperty("branch_id")
    @NotNull (message = "Branch id may not be null or empty")
    private Integer branch;
    @JsonProperty("employee_number")
    @NotNull (message = "Employee number may not be null or empty")
    private int employeeNumber;

    public EmployeeRequestDTO(){

    }

    public EmployeeRequestDTO(String name, String lastName, String cardNumber, long phoneNumber, String email,
                              AddressRequestDTO addressRequestDTO, Integer branch, int employeeNumber) {
        this.name = name;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressRequestDTO = addressRequestDTO;
        this.branch = branch;
        this.employeeNumber = employeeNumber;
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

    public AddressRequestDTO getAddressRequestDTO() {
        return addressRequestDTO;
    }

    public void setAddressRequestDTO(AddressRequestDTO addressRequestDTO) {
        this.addressRequestDTO = addressRequestDTO;
    }

    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer bank) {
        this.branch = bank;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
