package com.springjpa.springpostgresjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "consumable")
public class Consumable implements Serializable {

    private static final long serialVersionUID = 123456L;

    @Id
    private String itemName;

    @Column(name = "price")
    private double price;

    public Consumable(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public double getPrice(Consumable item) {
        return item.price;
    }
}
