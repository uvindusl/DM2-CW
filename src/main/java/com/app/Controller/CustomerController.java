package com.app.Controller;


import com.app.Entity.Customer;
import com.app.Service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId){
        return customerService.getCustomerById(customerId);
    }
}
