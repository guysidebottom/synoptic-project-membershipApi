package com.springjpa.springpostgresjpa.model;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;

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

    protected EmployeeCard(){}

    public EmployeeCard(int cardNumber, double balance) {
        if(cardNumber == 0) {
            throw new EntityCreationException("Cannot create card with invalid number %s", cardNumber);
        }
        this.cardNumber = cardNumber;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance){
        if(newBalance < 1.00 || newBalance > 75.00) {
            System.out.println("Balance can not be less than zero.");
        }
        else {
            this.balance = newBalance;
        }
    }

}
