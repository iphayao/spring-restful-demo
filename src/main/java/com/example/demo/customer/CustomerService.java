package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    public List<Customer> retrieveCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

    public List<Customer> retrieveCustomer(String name) {
        return customerRepository.findByFirstName(name);
    }

    public Customer retrieveCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        customer.setId(null);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        customer.setId(null);
        if(!customerRepository.existsById(id)) {
            return null;
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public boolean deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
