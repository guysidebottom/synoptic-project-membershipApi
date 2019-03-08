package com.springjpa.springpostgresjpa.model;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "pin_number")
    private int pinNumber;

    @Column(name = "card_number")
    private int cardNumber;

    protected Employee(){}

    public Employee(String name, String emailAddress, int phoneNumber, int pinNumber, int cardNumber) {
        if(name == null || emailAddress == null || phoneNumber == 0 || pinNumber == 0 || cardNumber == 0) {
            throw new EntityCreationException("Field can not be null %s",
                    name,
                    emailAddress,
                    phoneNumber,
                    pinNumber,
                    cardNumber);
        }
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, name='%s', email='%s', phone='%d']", id, name, emailAddress, phoneNumber);
    }
}
