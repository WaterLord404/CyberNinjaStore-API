package com.cyberninja.services;

import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.dto.CustomerDTO;
import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;

public interface CustomerServiceI {

	Customer createCustomer(CustomerDTO dto, User user);

	Customer getCustomer(UserDTO userDTO);

}
