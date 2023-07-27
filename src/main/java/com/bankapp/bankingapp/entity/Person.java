package com.bankapp.bankingapp.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public class Person {
    @Id
    private  Integer id;
    protected String name;
    protected String lastName;
    protected String cardNumber;
    protected long phoneNumber;
    protected String email;
    @OneToOne //Una persona tiene una dirección
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
}
