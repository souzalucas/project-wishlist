package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.demo.model.Product;
import com.project.demo.model.Wishlist;
import com.project.demo.service.WishlistService;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@PostMapping
	public Wishlist create(@RequestBody Wishlist wishlist) {
		return this.wishlistService.create(wishlist);
	}
	
	@PutMapping("/addProduct/{id}")
	public Wishlist addProduct(@PathVariable String id, @RequestBody ObjectNode body) {
		return this.wishlistService.addProduct(id, body);
	}
	
	@PutMapping("/removeProduct/{id}")
	public Wishlist removeProduct(@PathVariable String id, @RequestBody ObjectNode body) {
		return this.wishlistService.removeProduct(id, body);
	}
	
	@GetMapping("/products/{id}")
	public List<Product> getAllProducts(@PathVariable String id) {
		return wishlistService.getAllProducts(id);
	}
	
	@GetMapping("/product/{id}/{productId}")
	public void getProduct(@PathVariable String id, @PathVariable String productId) {
		wishlistService.getProduct(id, productId);
	}
}
