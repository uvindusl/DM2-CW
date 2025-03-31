package com.app.Entity;

public class Customer {
    private String customerName;
    private String customerAddress;
    private int customerTel;

    public Customer(String customerName, String customerAddress, int customerTel) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTel = customerTel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(int customerTel) {
        this.customerTel = customerTel;
    }
}
