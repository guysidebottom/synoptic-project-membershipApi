package com.springjpa.springpostgresjpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "staff_member")
public class StaffMember implements Serializable {

    private static final long serialVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    protected StaffMember(){}

    public StaffMember(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }
}
