package com.cyberninja.security.services;

import com.cyberninja.security.model.entity.dto.UserDTO;

public interface UserServiceI {

	UserDTO createUser(UserDTO dto);

	UserDTO getUser(UserDTO dto);

}
