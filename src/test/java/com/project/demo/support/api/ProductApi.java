package com.project.demo.support.api;

import com.project.demo.support.domain.Product;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductApi {
	
	private static final String CREATE_PRODUCT_ENDPOINT = "/products";

	public List<Product> createProducts(Integer numProducts) {
		
		List<Product> products = new ArrayList<Product>();
		
		Map<String, String> body = new HashMap<>();
		
		for (int i = 0; i < numProducts; i++) {
			
			body.put("name", "Product " + Integer.toString(i));
						
			products.add(given()
			  		.contentType(ContentType.JSON)
			  		.body(body)
			  	.when()
			  		.post(CREATE_PRODUCT_ENDPOINT)
			  	.then()
			  		.extract().body().as(Product.class));			
		}
		return products;
	}
	
}
