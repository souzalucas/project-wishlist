package com.project.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.demo.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
