package com.caching.crm_geode_2.controller;

import com.caching.crm_geode_2.model.Customer;
import com.caching.crm_geode_2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private static final String HTML = "<H1>%s</H1>";

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public Iterable<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @PostMapping("/customers")
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @GetMapping("/customers/{name}")
    public Customer findByName(@PathVariable("name") String name) {
        return this.customerRepository.findByNameLike(name);
    }

    @GetMapping("/")
    public String home() {
        return format("Customer Relationship Management");
    }

    @GetMapping("/ping")
    public String ping() {
        return format("PONG");
    }

    private String format(String value) {
        return String.format(HTML, value);
    }
}