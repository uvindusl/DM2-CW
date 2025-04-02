package com.app.Entity;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeeAddress;
    private int employeeTel;
    private String employeePassword;

    public Employee(int employeeId, String employeeName, String employeeAddress, int employeeTel, String employeePassword) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeTel = employeeTel;
        this.employeePassword = employeePassword;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public int getEmployeeTel() {
        return employeeTel;
    }

    public void setEmployeeTel(int employeeTel) {
        this.employeeTel = employeeTel;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
}
