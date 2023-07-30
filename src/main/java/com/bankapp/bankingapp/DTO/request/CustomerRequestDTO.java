package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CustomerRequestDTO {

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
    protected AddressRequestDTO addressRequestDTO;
    @JsonProperty("customer_number")
    @NotNull
    private int customerNumber;

    public CustomerRequestDTO(){ }
    public CustomerRequestDTO(String name, String lastName, String cardNumber, long phoneNumber,
                              String email, AddressRequestDTO addressRequestDTO, int customerNumber) {
        this.name = name;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressRequestDTO = addressRequestDTO;
        this.customerNumber = customerNumber;
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

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
}
