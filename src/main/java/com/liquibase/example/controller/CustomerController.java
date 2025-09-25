package com.liquibase.example.controller;


import com.liquibase.example.model.Customer;
import com.liquibase.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity<>("No customers found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer.getId() == null) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCustomer(Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        if(savedCustomer == null) {
            return new ResponseEntity<>("Check your fields correctly", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCustomer(Long id, Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        if (updatedCustomer == null) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(Long id) {
        boolean result = customerService.deleteCustomer(id);
        if(!result) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }
}
