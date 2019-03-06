package com.springjpa.springpostgresjpa.model;

import java.util.List;

public class Order {

    private List<Consumable> items;
    private double totalPrice;
    private boolean isValidOrder;

    public void addItem(Consumable item) {
        totalPrice += item.getPrice(item);
        items.add(item);
    }

    public void removeItem(Consumable item) {
        totalPrice -= item.getPrice(item);
        items.remove(item);
    }
}
