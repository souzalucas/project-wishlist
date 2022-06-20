package com.project.demo.support.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.project.demo.support.domain.Product;
import com.project.demo.support.domain.Wishlist;

import io.restassured.http.ContentType;

public class WishlistApi {
	
	private static final String CREATE_WISHLIST_ENDPOINT = "/wishlists";
	private static final String ADD_PRODUCT_IN_WISHLIST_ENDPOINT = "/wishlists/addProduct/";
	private static final String REMOVE_PRODUCT_FROM_WISHLIST_ENDPOINT = "/wishlists/removeProduct/";
	private static final String GET_ALL_PRODUCTS_FROM_WISHLIST_ENDPOINT = "/wishlists/products/";
	private static final String GET_PRODUCT_FROM_WISHLIST_ENDPOINT = "/wishlists/product/";

	public Wishlist addProduct(Wishlist wishlist, Product product) {
		
		Map<String, String> body = new HashMap<>();
		body.put("productId", product.getId());
		
		return given()
			  		.contentType(ContentType.JSON)
			  		.body(body)
			  	.when()
			  		.put(ADD_PRODUCT_IN_WISHLIST_ENDPOINT + wishlist.getId())
			  	.then()
			  		.extract().body().as(Wishlist.class);
	}
	
	public int addProductBadRequest(Wishlist wishlist, Product product) {
		
		Map<String, String> body = new HashMap<>();
		body.put("productId", product.getId());
		
		return given()
			  		.contentType(ContentType.JSON)
			  		.body(body)
			  	.when()
			  		.put(ADD_PRODUCT_IN_WISHLIST_ENDPOINT + wishlist.getId())
			  	.then()
			  	.statusCode(HttpStatus.SC_BAD_REQUEST)
			  	.extract().statusCode();
	}
	
	public Wishlist removeProduct(Wishlist wishlist, Product product) {
		
		Map<String, String> body = new HashMap<>();
		body.put("productId", product.getId());
		
		return given()
			  		.contentType(ContentType.JSON)
			  		.body(body)
			  	.when()
			  		.put(REMOVE_PRODUCT_FROM_WISHLIST_ENDPOINT + wishlist.getId())
			  	.then()
			  		.extract().body().as(Wishlist.class);
	}
	
	public List<Product> getAllProducts(Wishlist wishlist) {
		
		return given()
			  	.when()
			  		.get(GET_ALL_PRODUCTS_FROM_WISHLIST_ENDPOINT + wishlist.getId())
			  	.then()
			  		.extract().body().jsonPath().getList("products", Product.class);
	}
	
	public int getProduct(Wishlist wishlist, Product product) {
		
		return given()
			  	.when()
			  		.get(GET_PRODUCT_FROM_WISHLIST_ENDPOINT + wishlist.getId() + "/" + product.getId())
			  	.then()
			  		.extract().statusCode();
	}
	
	public int getProductBadRequest(Wishlist wishlist, Product product) {
		
		return given()
			  	.when()
			  		.get(GET_PRODUCT_FROM_WISHLIST_ENDPOINT + wishlist.getId() + "/" + product.getId())
			  	.then()
			  		.statusCode(HttpStatus.SC_BAD_REQUEST)
			  		.extract().statusCode();
	}
	
	public Wishlist createWishlist(Integer limit) {
		
		Map<String, Integer> body = new HashMap<>();
		body.put("limit", limit);
		
		return given()
		  		.contentType(ContentType.JSON)
		  		.body(body)
		  	.when()
		  		.post(CREATE_WISHLIST_ENDPOINT)
		  	.then()
		  		.extract().body().as(Wishlist.class);
	}
	
	public void addProducts(Wishlist wishlist, Integer numProducts, List<Product> products) {

		Map<String, String> body = new HashMap<>();

		for (int i = 0; i < numProducts; i++) {
			
			body.put("productId", products.get(i).getId().toString());

			given()
				.contentType(ContentType.JSON)
		  		.body(body)
		  	.when()
		  		.put(ADD_PRODUCT_IN_WISHLIST_ENDPOINT + wishlist.getId())
		  	.then()
		  		.extract().body().as(Wishlist.class);			
		}
		return;
		
	}
}
