package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Customer> retrieveCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> retrieveCustomer(String name) {
        return customerRepository.findByFirstName(name);
    }

    public Customer createCustomer(Customer customer) {
        customer.setId(null);
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()) {
            return customerOptional;
        }
        customer.setId(id);
        return Optional.of(customerRepository.save(customer));
    }

    public boolean deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
