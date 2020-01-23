package com.springjpa.springpostgresjpa.model;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {

    private static final long serialVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "name")
    private String name;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "pin_number")
    private int pinNumber;

    @Column(name = "balance")
    private double balance;

    private boolean isLoggedIn = false;

    protected EmployeeEntity() {
    }

    public EmployeeEntity(int id, String cardId, String name, String emailAddress, String phoneNumber, int pinNumber) {
        if (cardId.equals("")  || name.equals("") || emailAddress.equals("") || phoneNumber.equals("") || pinNumber == 0) {
            throw new EntityCreationException("Field can not be null %s",
                    id,
                    cardId,
                    name,
                    emailAddress,
                    phoneNumber,
                    pinNumber);
        }
        setCardId(cardId);
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.pinNumber = pinNumber;
        this.balance = 0.0;
    }

    // Ensure that staff ID's conform to alphanumeric pattern upto 16 chars long
    public void setCardId(String newId) throws EntityCreationException {
        String nameRegex = "([a-zA-Z0-9])\\w{15}";

        if (newId.matches(nameRegex)) {
            this.cardId = newId;
        } else throw new EntityCreationException("Not a valid card ID. Must be alphanumeric 16 characters long");
    }

    // emails must conform to email pattern with an '@'
    public void setEmailAddress(String emailAddress) {
        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(emailAddress);

        if (matcher.matches()) {
            this.emailAddress = emailAddress;
        } else throw new EntityCreationException("Invalid email format");
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        int pinLength = String.valueOf(pinNumber).length();
        if (pinLength == 4) {
            this.pinNumber = pinNumber;
        } else throw new EntityCreationException("PIN must be 4 digits long");
    }

    public String getCardId() {
        return this.cardId;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", pinNumber=" + pinNumber +
                '}';
    }

    public void setId(long id) {
    }
}
