package com.cyberninja.services.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.converter.CustomerConverter;
import com.cyberninja.model.entity.dto.CustomerDTO;
import com.cyberninja.model.repository.CustomerRepository;
import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.services.entity.CartServiceI;
import com.cyberninja.services.entity.CustomerServiceI;

@Service
public class CustomerServiceImpl implements CustomerServiceI {
	
	@Autowired private CustomerRepository customerRepo;

	@Autowired private CustomerConverter customerConverter;

	@Autowired private CartServiceI cartService;
	
	/**
	 * Crea un customer
	 */
	@Override
	public Customer createCustomer(CustomerDTO dto, User user) {
		Customer customer = customerConverter.customerDTOToCustomer(dto);
	
		cartService.createCart(customer);
		
		customer.setUser(user);
		user.setCustomer(customer);
		
		customerRepo.save(customer);

		return customer;
	}
	
	/**
	 * Obtiene un customer
	 */
	@Override
	public Customer getCustomer(UserDTO dto) {
		return customerRepo.getCustomerByUserUsername(dto.getUsername());
	}
}
