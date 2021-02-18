package com.techshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshop.model.entity.Customer;
import com.techshop.model.entity.dto.CustomerDTO;
import com.techshop.model.entity.dto.CustomerDTOConverter;
import com.techshop.model.repository.CustomerRepository;
import com.techshop.security.model.entity.User;
import com.techshop.services.CustomerServiceI;

@Service
public class CustomerServiceImpl implements CustomerServiceI {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CustomerDTOConverter customerConverter;

	@Override
	public Customer createCustomer(CustomerDTO dto, User user) {
		Customer customer = customerConverter.customerDTOToCustomer(dto);
		
		customer.setUser(user);
		user.setCustomer(customer);
		
		customerRepo.save(customer);

		return customer;
	}
	
	@Override
	public CustomerDTO getCustomerDTO(Customer customer) {
		return customerConverter.CustomerToCustomerDTO(customer);
	}
}
