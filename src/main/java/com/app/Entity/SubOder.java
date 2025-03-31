package com.app.Entity;

public class SubOder {
    private int customerId;
    private int foodId;
    private int qty;
    private int orderId;

    public SubOder(int customerId, int foodId, int qty, int orderId) {
        this.customerId = customerId;
        this.foodId = foodId;
        this.qty = qty;
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
