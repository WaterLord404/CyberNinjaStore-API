package com.techshop.security.services;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techshop.model.entity.Customer;
import com.techshop.model.entity.dto.CustomerDTOConverter;
import com.techshop.model.repository.CustomerRepository;
import com.techshop.security.model.entity.User;
import com.techshop.security.model.entity.dto.UserDTO;
import com.techshop.security.model.entity.dto.UserDTOConverter;
import com.techshop.security.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private UserDTOConverter userConverter;

	@Autowired
	private CustomerDTOConverter customerConverter;

	/**
	 * Busca User por nombre
	 * 
	 * @param username
	 * @return User
	 * @throws UsernameNotFoundException
	 */
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
	public UserDTO createNewUser(UserDTO dto) {
		User user = new User();
		Customer customer = new Customer();
		
		// DTO to Entity
		user = userConverter.userDTOToUser(dto);
		customer = customerConverter.customerDTOToCustomer(dto.getCustomer());
		
		// Relacion 1-1
		customer.setUser(user);
		user.setCustomer(customer);
		
		customerRepo.save(customer);
		
		// Entity to DTO
		dto = userConverter.userToUserDTO(user);
		dto.setCustomer(customerConverter.CustomerToCustomerDTO(customer));
		return dto;
	}

	/**
	 * Busca User por id
	 * 
	 * @param idUser
	 * @return User
	 * @throws AuthenticationException
	 */
	public UserDetails loadUserById(Long idUser) throws AuthenticationException {
		return userRepo.findById(idUser).orElseThrow(() -> new AuthenticationException("Id/username not found"));
	}

}
