package com.project.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.demo.model.Wishlist;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

}
