package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

public class CustomerRequestDTO {

    private Integer id;

    protected String name;
    @JsonProperty("last_name")
    protected String lastName;
    @JsonProperty("card_number")
    protected String cardNumber;
    @JsonProperty("phone_number")
    protected Long phoneNumber;

    @Email(message = "Email should be valid")
    protected String email;
    @JsonProperty("address")
    protected AddressRequestDTO addressRequestDTO;
    @JsonProperty("customer_number")
    private Integer customerNumber;

    public CustomerRequestDTO(){ }
    public CustomerRequestDTO(String name, String lastName, String cardNumber, Long phoneNumber,
                               String email, AddressRequestDTO addressRequestDTO, Integer customerNumber, Integer id) {
        this.name = name;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressRequestDTO = addressRequestDTO;
        this.customerNumber = customerNumber;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
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

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
