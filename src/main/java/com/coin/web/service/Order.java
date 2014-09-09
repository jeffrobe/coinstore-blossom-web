package com.coin.web.service;


import java.util.List;

import com.coin.model.User;
 
public class Order {

    private User customer;
    private List<OrderRow> rows;

    public Order(User customer, List<OrderRow> rows) {
        this.customer = customer;
        this.rows = rows;
    }

    public User getCustomer() {
        return customer;
    }

    public List<OrderRow> getRows() {
        return rows;
    }
}
