package com.coin.web.service;

/**
 * A row in an order.
 */
public class OrderRow {

    private String articleCode;
    private int quantity;

    public OrderRow(String articleCode, int quantity) {
        this.articleCode = articleCode;
        this.quantity = quantity;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public int getQuantity() {
        return quantity;
    }
}
