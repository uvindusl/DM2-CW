package com.app.Entity;

public class Order {

    private int id;
    private int orderTotalPrice;
    private int orderCustomerId;
    private String orderstatus;

    public Order(int id, int orderTotalPrice, int orderCustomerId, String orderstatus) {
        this.id = id;
        this.orderTotalPrice = orderTotalPrice;
        this.orderCustomerId = orderCustomerId;
        this.orderstatus = orderstatus;
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

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
