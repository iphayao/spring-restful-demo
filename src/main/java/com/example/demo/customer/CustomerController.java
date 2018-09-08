package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerService.retrieveCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.retrieveCustomer(id);
        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public List<Customer> getCustomer(@RequestParam(value = "name") String name) {
        return customerService.retrieveCustomer(name);
    }

    @PostMapping()
    public ResponseEntity<?> postCustomer(@Valid @RequestBody Customer body) {
        Customer customer = customerService.createCustomer(body);
        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCustomer(@PathVariable Long id, @Valid @RequestBody Customer body) {
        Customer customer = customerService.updateCustomer(id, body);
        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        if(!customerService.deleteCustomer(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
