package com.app.Controller;

import com.app.Entity.Customer;
import com.app.Entity.Employee;
import com.app.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
