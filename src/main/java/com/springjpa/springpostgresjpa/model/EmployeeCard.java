package com.springjpa.springpostgresjpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_card")
public class EmployeeCard implements Serializable {

    private static final long serialVersionUID = 123456L;

    @Id
    private int cardNumber;

    // new employee cards always instantiated with zero balance
    @Column(name = "balance")
    private double balance = 0;

    protected EmployeeCard(){}

    public EmployeeCard(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setBalance(double newBalance){
        if(newBalance < 0) {
            System.out.println("Balance can not be less than zero.");
        }
        else {
            this.balance = newBalance;
        }
    }

}
