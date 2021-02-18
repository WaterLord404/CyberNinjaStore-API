package com.cyberninja.services;

import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.dto.CustomerDTO;
import com.cyberninja.security.model.entity.User;

public interface CustomerServiceI {

	public Customer createCustomer(CustomerDTO dto, User user);

	public CustomerDTO getCustomerDTO(Customer customer);

}
