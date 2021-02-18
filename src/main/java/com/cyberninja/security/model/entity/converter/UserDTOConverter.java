package com.cyberninja.security.model.entity.converter;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.security.model.entity.enun.UserRole;

@Component
public class UserDTOConverter {

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
		user.setCreateTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		user.setLastPasswordChange(LocalDateTime.now());
		user.setLocked(false);
		user.setEnabled(true);
		user.setAuthenticationAttempts(0);
		user.setPasswordPolicyExpDate(LocalDateTime.now().plusDays(180));

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
		dto.setRoles(user.getRoles());

		return dto;
	}

}
