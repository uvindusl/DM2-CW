package com.app.Entity;

public class Supplier {
    private int id;
    private String name;
    private String address;
    private int tell;
    private String company;
    private String password;

    public Supplier(){
        this.id = id;
        this.name = name;
        this.address = address;
        this.tell = tell;
        this.company = company;
        this.password = password;
    }
    public Supplier(String name, String address, int tell, String company, String password) {

        this.name = name;
        this.address = address;
        this.tell = tell;
        this.company = company;
        this.password = password;
    }


    public Supplier(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTell() {
        return tell;
    }

    public void setTell(int tell) {
        this.tell = tell;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
