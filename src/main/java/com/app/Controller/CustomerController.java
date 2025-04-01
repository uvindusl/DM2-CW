package com.app.Controller;


import com.app.Entity.Customer;
import com.app.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path="/customers/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId){
        return customerService.getCustomerById(customerId);
    }

    @GetMapping(path="/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping(path="/customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }
}
