package com.app.Entity;

public class SubOder {
    private int id;
    private int foodId;
    private int qty;
    private int orderId;
    private int supplierId;
    private String status;

    public SubOder(int id, int foodId, int qty, int orderId, int supplierId , String status) {
        this.id = id;
        this.foodId = foodId;
        this.qty = qty;
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.status = status;
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
