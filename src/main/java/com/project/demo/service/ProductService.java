package com.project.demo.service;

import org.springframework.stereotype.Service;

import com.project.demo.model.Product;

@Service
public interface ProductService {
	
	public Product create(Product product);

}
