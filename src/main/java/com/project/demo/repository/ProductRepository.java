package com.project.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.demo.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
