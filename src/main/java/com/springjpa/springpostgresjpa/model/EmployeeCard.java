package com.springjpa.springpostgresjpa.model;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_card")
public class EmployeeCard implements Serializable {

    private static final long serialVersionUID = 123456L;

    @Id
    private int cardNumber;

    @Column(name = "balance")
    private double balance;

    protected EmployeeCard() {
    }

    public EmployeeCard(int cardNumber, double balance) {
        if (cardNumber == 0) {
            throw new EntityCreationException("Cannot create card with invalid number %s", cardNumber);
        }
        this.cardNumber = cardNumber;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public ResponseEntity<Object> setBalance(double newBalance) {
        if (newBalance < 1.00 || newBalance > 75.00) {
            System.out.println("Balance must be between 1 and 75.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            this.balance = newBalance;
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
