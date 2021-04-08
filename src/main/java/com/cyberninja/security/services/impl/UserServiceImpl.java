package com.cyberninja.security.services.impl;

import static org.springframework.http.HttpStatus.CONFLICT;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.converter.CustomerConverter;
import com.cyberninja.security.model.entity.User;
import com.cyberninja.security.model.entity.converter.UserConverter;
import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.security.model.repository.UserRepository;
import com.cyberninja.security.services.UserServiceI;
import com.cyberninja.services.entity.CustomerServiceI;

@Service
public class UserServiceImpl implements UserDetailsService , UserServiceI{

	@Autowired private UserRepository userRepo;

	@Autowired private UserConverter userConverter;

	@Autowired private CustomerServiceI customerService;

	@Autowired private CustomerConverter customerConverter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

	/**
	 * Crea un nuevo User con su Customer
	 * 
	 * @param dto
	 * @return UserDTO
	 */
	@Override
	public UserDTO createUser(UserDTO dto) {
		// Verifica que no exista el usuario
		if (userRepo.findUserByUsername(dto.getUsername()) != null) {
			throw new ResponseStatusException(CONFLICT);
		}
		
		User user = userConverter.userDTOToUser(dto);
		Customer customer = customerService.createCustomer(dto.getCustomer(), user);
		// Entity to DTO
		dto = userConverter.userToUserDTO(user);
		dto.setCustomer(customerConverter.CustomerToCustomerDTO(customer));
		
		return dto;
	}
	
	/**
	 * Devuelve un UserDTO
	 */
	@Override
	public UserDTO getUser(UserDTO dto) {
		Customer customer = customerService.getCustomer(dto);
		dto.setCustomer(customerConverter.CustomerToCustomerDTO(customer));
		
		return dto;
	}

	public UserDetails loadUserById(Long idUser) throws AuthenticationException {
		return userRepo.findById(idUser).orElseThrow(() -> new AuthenticationException("Id or username not found"));
	}

}