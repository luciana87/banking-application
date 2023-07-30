package com.bankapp.bankingapp.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected  Integer id;

    protected String name;
    protected String lastName;
    protected String cardNumber;
    protected long phoneNumber;
    protected String email;

    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //Una persona tiene una dirección
    @JoinColumn(name = "address_id")
    protected Address address;

    public Person() {
    }

    public Person(String name, String lastName, String cardNumber, long phoneNumber, String email, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Integer getId() {
        return id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
