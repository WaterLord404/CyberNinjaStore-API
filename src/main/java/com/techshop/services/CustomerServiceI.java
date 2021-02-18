package com.techshop.services;

import com.techshop.model.entity.Customer;
import com.techshop.model.entity.dto.CustomerDTO;
import com.techshop.security.model.entity.User;

public interface CustomerServiceI {

	public Customer createCustomer(CustomerDTO dto, User user);

	public CustomerDTO getCustomerDTO(Customer customer);

}
