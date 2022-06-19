package com.project.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customer")
public class Customer {

	@Id
	private String id;
	
	private String name;

	@DBRef
	private Wishlist wishlist;
	
	public Customer(String id, Wishlist wishlist) {
		this.id = id;
		this.wishlist = wishlist;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Wishlist getWishlist() {
		return this.wishlist;
	}
	
	public void setWishlist(Wishlist wishlist) {
		this.wishlist= wishlist;
	}
}
