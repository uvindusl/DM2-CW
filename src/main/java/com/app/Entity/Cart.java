package com.app.Entity;


public class Cart {
    private int customerId;
    private int foodId;
    private int qty;
    private int subTotal;

    public Cart(int customerId, int foodId, int qty, int subTotal) {
        this.customerId = customerId;
        this.foodId = foodId;
        this.qty = qty;
        this.subTotal = subTotal;
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

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }


}
