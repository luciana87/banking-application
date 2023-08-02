package com.bankapp.bankingapp.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class AddressRequestDTO {
    @NotEmpty (message = "Street may not be null or empty")
    private String street;
    @NotEmpty (message = "City may not be null or empty")
    private String city;
    @NotEmpty (message = "State may not be null or empty")
    private String state;
    @JsonProperty("postal_code")
    @NotEmpty (message = "Postal code may not be null or empty")
    private String postalCode;
    @NotEmpty (message = "Country may not be null or empty")
    private String country;

    public AddressRequestDTO(String street, String city, String state, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
