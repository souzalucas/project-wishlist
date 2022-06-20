package com.project.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.demo.model.Product;
import com.project.demo.model.Wishlist;

@Service
public interface WishlistService {
	
	public Wishlist create(Wishlist wishlist);
	
	public Wishlist addProduct(String wishlistId, ObjectNode body);
	
	public Wishlist removeProduct(String wishlistId, ObjectNode body);
	
	public List<Product> getAllProducts(String wishlistId);
	
	public void getProduct(String wishlistId, String productId);
	
}
