package com.app.Controller;

import com.app.Entity.Employee;
import com.app.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path="/employees/{employeeId}")
    public Employee getEmployeeByID(@PathVariable int employeeId){
            return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping(path="/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping(path="/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping(path="/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId,@RequestBody Employee employee){
        return employeeService.updateEmployee(employeeId,employee);
    }

    @DeleteMapping(path="/employees/{employeeId}")
    public Employee deleteEmployee(@PathVariable int employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

    @PostMapping("/employees/login")
    private ResponseEntity<Employee> loginEmployee(@RequestBody LoginRequest loginRequest){
        Employee employee = employeeService.loginEmployee(loginRequest.getEmployeeName(), loginRequest.getEmployeePassword());
        if(employee != null){
            return ResponseEntity.ok(employee);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    static class LoginRequest{
        private String employeeName;
        private String employeePassword;

        public String getEmployeeName(){
            return employeeName;
        }

        public void setEmployeeName(String employeeName){
            this.employeeName = employeeName;
        }

        public String getEmployeePassword(){
            return employeePassword;
        }

        public void setEmployeePassword(String employeePassword){
            this.employeePassword = employeePassword;
        }
    }
}
