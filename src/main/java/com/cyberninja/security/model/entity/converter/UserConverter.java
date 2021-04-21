package com.cyberninja.security.model.entity.converter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.security.model.entity.enumerated.UserRole;

@Component
public class UserConverter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Convierte UserDTO a User
	 * 
	 * @param dto
	 * @return User
	 */
	public User userDTOToUser(UserDTO dto) {
		User user = new User();
		
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRoles(Set.of(UserRole.USER));
		user.setCreationDate(LocalDateTime.now());
		user.setUpdateDate(LocalDateTime.now());
		user.setLastPasswordChange(LocalDateTime.now());
		user.setLocked(false);
		user.setEnabled(false);
		user.setAuthenticationAttempts(0);
		user.setPasswordPolicyExpDate(LocalDateTime.now().plusDays(180));
		user.setConfirmationToken(UUID.randomUUID().toString());
		
		return user;
	}

	/**
	 * Convierte User a User DTO y asigna el CustomerDTO
	 * 
	 * @param user
	 * @return UserDTO
	 */
	public UserDTO userToUserDTO(User user) {
		UserDTO dto = new UserDTO();

		dto.setUsername(user.getUsername());

		return dto;
	}

}
