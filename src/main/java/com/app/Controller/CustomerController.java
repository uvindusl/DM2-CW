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

    @PutMapping(path="/customers/{customerId}")
    public Customer updateCustomer(@PathVariable int customerId, @RequestBody Customer customer){
        return customerService.updateCustomer(customerId,customer);
    }

    @DeleteMapping(path="/customers/{customerId}")
    public Customer deletCustomer(@PathVariable int customerId){
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping(path="/customers", params={"customerName", "customerTel"})
    public Customer loginCustomer(@RequestParam String customerName,@RequestParam int customerTel){
        return customerService.loginCustomer(customerName,customerTel);
    }
    //http://localhost:8080/customers?customerName=uvindu&customerTel=1234567890
}
