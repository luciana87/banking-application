package com.bankapp.bankingapp.entity;

import com.bankapp.bankingapp.exceptions.ResourceNotFoundException;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address() {
    }

    public Address(String street, String city, String state, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Integer getId() {
        return id;
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

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "street" :
                this.street = (String) newValue;
                break;
            case "city" :
                this.city = (String) newValue;
                break;
            case "state" :
                this.state = (String) newValue;
                break;
            case "postal_code" :
                this.postalCode = (String) newValue;
                break;
            case "country" :
                this.country = (String) newValue;
                break;
            default:
                throw new ResourceNotFoundException("No se permite modificar un campo solicitado");
        }
    }
}
