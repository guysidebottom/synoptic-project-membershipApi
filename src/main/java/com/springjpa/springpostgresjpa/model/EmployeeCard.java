package com.springjpa.springpostgresjpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee_card")
public class EmployeeCard implements Serializable {

    private static final long serialVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardNumber;

    // new employee cards always instantiated with zero balance
    @Column(name = "balance")
    private double balance = 0;

    // new employee cards always instantiated as not being registered
    @Column(name = "registered")
    private boolean isRegistered = false;

    protected EmployeeCard(){}

    public void setBalance(double newBalance){
        if(newBalance < 0) {
            System.out.println("Balance can not be less than zero.");

        }
    }

    public void setRegistration(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

}
