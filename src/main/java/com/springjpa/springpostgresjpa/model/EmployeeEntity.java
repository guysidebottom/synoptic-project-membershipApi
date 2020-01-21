package com.springjpa.springpostgresjpa.model;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import javafx.beans.binding.ObjectExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    private long id;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "name")
    private String name;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "pin_number")
    private int pinNumber;

    @Column(name = "balance")
    private double balance;

    protected EmployeeEntity(){}

    public EmployeeEntity(String cardId, String name, String emailAddress, int phoneNumber, int pinNumber) {
        if(cardId == null || name == null || emailAddress == null || phoneNumber == 0 || pinNumber == 0) {
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

    public Long id() {
        return id;
    }

// Ensure that staff ID's conform to alphanumeric pattern upto 16 chars long
    public ResponseEntity<Object> setCardId(String newId) throws EntityCreationException {
        String nameRegex = "([a-zA-Z0-9])\\w{15}";

        if(newId.matches(nameRegex)) {
            this.cardId = newId;
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new EntityCreationException();
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
// emails must conform to email pattern with an '@'
    public ResponseEntity<Object> setEmailAddress(String emailAddress) {
        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(emailRegex);

        if(matcher.matches()){
            this.emailAddress = emailAddress;
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        return "Employee{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", pinNumber=" + pinNumber +
                '}';
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setId(long id) {
    }
}
