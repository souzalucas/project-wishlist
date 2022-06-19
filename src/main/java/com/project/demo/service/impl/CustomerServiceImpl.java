package com.project.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.model.Customer;
import com.project.demo.model.Wishlist;
import com.project.demo.repository.CustomerRepository;
import com.project.demo.repository.WishlistRepository;
import com.project.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Override
	public Customer create(Customer customer) {
		
		Wishlist wishlist = this.wishlistRepository
				.findById(customer.getWishlist().getId())
				.orElseThrow(() -> new IllegalArgumentException("Wishlist does not exist."));
		
		customer.setWishlist(wishlist);
		
		return this.customerRepository.save(customer);
	}

}
