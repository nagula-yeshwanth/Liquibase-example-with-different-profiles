package com.liquibase.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).orElse(new Customer());
    }

    public Customer saveCustomer(Customer customer) {
        try {
            return customerRepo.save(customer);
        } catch (Exception e) {
            return null;
        }
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepo.findById(id).orElse(new Customer());
        if (customer.getId() == null) {
            return null;
        }
        if(customerDetails.getName() != null){
            customer.setName(customerDetails.getName());
        }
        if(customerDetails.getPhoneNumber() != null){
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
        }
        if(customerDetails.getEmail() != null) {
            customer.setEmail(customerDetails.getEmail());
        }
        return customerRepo.save(customer);
    }

    public boolean deleteCustomer(Long id) {
        if(!customerRepo.existsById(id)) {
            return false;
        }
        customerRepo.deleteById(id);
        return true;
    }
}
