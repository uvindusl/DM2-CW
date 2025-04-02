package com.app.Controller;

import com.app.Entity.Employee;
import com.app.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path="/employee/{employeeId}")
    public Employee getEmployeeByID(@PathVariable int employeeId){
            return employeeService.getEmployeeById(employeeId);
    }
}
