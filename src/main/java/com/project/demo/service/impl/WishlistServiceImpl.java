package com.project.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.demo.model.Product;
import com.project.demo.model.Wishlist;
import com.project.demo.repository.ProductRepository;
import com.project.demo.repository.WishlistRepository;
import com.project.demo.service.WishlistService;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    protected MongoTemplate mongoTemplate;
	
	@Override
	public Wishlist create(Wishlist wishlist) {
		return this.wishlistRepository.save(wishlist);
	}

	@Override
	public Wishlist addProduct(String wishlistId, ObjectNode body) {
		
		Wishlist wishlist = this.wishlistRepository
				.findById(wishlistId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wishlist does not exist."));
						
		Product product = this.productRepository
				.findById(body.get("productId").asText())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product does not exist."));
		
		if (wishlist.productContains(product)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is already on the wishlist.");			
		}
		
		if (wishlist.itsFull()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wishlist is full. Maximum capacity: " + wishlist.getLimit());
		}
		
		mongoTemplate.updateFirst(
				Query.query(Criteria.where("id").is(wishlistId)),
				new Update().push("products", product), Wishlist.class);
		
		return mongoTemplate.findOne(Query.query(Criteria.where("id").is(wishlistId)), Wishlist.class);
	}
	
	@Override
	public Wishlist removeProduct(String wishlistId, ObjectNode body) {
		
		this.wishlistRepository
				.findById(wishlistId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wishlist does not exist."));
								
		Product product = this.productRepository
				.findById(body.get("productId").asText())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wishlist does not exist."));		
				
		mongoTemplate.updateFirst(
				Query.query(Criteria.where("id").is(wishlistId)),
				new Update().pull("products", product), Wishlist.class);
		
		return mongoTemplate.findOne(Query.query(Criteria.where("id").is(wishlistId)), Wishlist.class);
	}
	
	@Override
	public List<Product> getAllProducts(String wishlistId) {
		
		Wishlist wishlist = this.wishlistRepository
				.findById(wishlistId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wishlist does not exist."));
		
		return wishlist.getProducts();
	}

	@Override
	public void getProduct(String wishlistId, String productId) {
		
		Wishlist wishlist = this.wishlistRepository
				.findById(wishlistId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wishlist does not exist."));
				
		Product product = this.productRepository
				.findById(productId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product does not exist."));
		
		if (wishlist.productContains(product)) {
			throw new ResponseStatusException(HttpStatus.OK, "The product is in the wishlist");			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is not on the wishlist");			
		}
	}
}
