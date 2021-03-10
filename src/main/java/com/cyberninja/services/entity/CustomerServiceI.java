package com.cyberninja.services.entity;

import com.cyberninja.model.Customer;
import com.cyberninja.model.dto.CustomerDTO;
import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;

public interface CustomerServiceI {

	Customer createCustomer(CustomerDTO dto, User user);

	Customer getCustomer(UserDTO userDTO);

}
