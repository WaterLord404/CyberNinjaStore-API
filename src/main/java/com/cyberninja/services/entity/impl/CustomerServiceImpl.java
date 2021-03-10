package com.cyberninja.services.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.Customer;
import com.cyberninja.model.converter.CustomerConverter;
import com.cyberninja.model.dto.CustomerDTO;
import com.cyberninja.repository.CustomerRepository;
import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.services.entity.CustomerServiceI;

@Service
public class CustomerServiceImpl implements CustomerServiceI {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CustomerConverter customerConverter;

	/**
	 * Crea un customer
	 */
	@Override
	public Customer createCustomer(CustomerDTO dto, User user) {
		Customer customer = customerConverter.customerDTOToCustomer(dto);
		
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
