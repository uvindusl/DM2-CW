package com.app.Entity;

public class Order {

    private int id;
    private int orderTotalPrice;
    private int orderCustomerId;


    public Order(int id, int orderTotalPrice, int orderCustomerId) {
        this.id = id;
        this.orderTotalPrice = orderTotalPrice;
        this.orderCustomerId = orderCustomerId;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getOrderCustomerId() {
        return orderCustomerId;
    }

    public void setOrderCustomerId(int orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
