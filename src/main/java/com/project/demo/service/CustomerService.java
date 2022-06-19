package com.project.demo.service;

import org.springframework.stereotype.Service;

import com.project.demo.model.Customer;

@Service
public interface CustomerService {
	
	public Customer create(Customer customer);
}
