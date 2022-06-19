package com.project.demo.support.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.project.demo.model.Product;

public class Wishlist {
private String id;
	
	private Integer limit;
		
	@DBRef
	private List<Product> products;
	
	public Wishlist(String id, Integer limit) {
		this.id = id;
		this.limit = limit;
		this.products = new ArrayList<Product>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Integer getLimit() {
		return this.limit;
	}
	
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	public List<Product> getProducts() {
		return this.products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
