package com.cyberninja.security.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.cyberninja.security.model.entity.dto.UserDTO;

public interface UserServiceI {

	UserDTO createUser(UserDTO dto);

	UserDTO getUser(UserDTO dto);

	UserDTO confirmAccount(String token);

	void loginGoogle(String token, HttpServletResponse response) throws IOException;

}
