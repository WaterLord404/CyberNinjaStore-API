package com.cyberninja.security.services.impl;

import static com.cyberninja.security.common.SecurityConstants.GOOGLE_CLIENTID;
import static com.cyberninja.security.common.SecurityConstants.HEADER_STRING;
import static com.cyberninja.security.common.SecurityConstants.TOKEN_PREFIX;
import static com.cyberninja.security.filter.jwt.JWTTokenProvider.generateToken;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.io.IOException;
import java.util.Collections;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

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
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class UserServiceImpl implements UserDetailsService, UserServiceI {

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
			throw new ResponseStatusException(CONFLICT, "This username already exists");
		}

		User user = userConverter.userDTOToUser(dto);
		Customer customer = customerService.createCustomer(dto.getCustomer(), user);
		// Entity to DTO
		dto = userConverter.userToUserDTO(user);
		dto.setCustomer(customerConverter.CustomerToCustomerDTO(customer));

		return dto;
	}

	/**
	 * Obtiene un usuario por id
	 */
	@Override
	public User getUserById(Long id) {
		return userRepo.findUserByIdAndEnabled(id, true)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
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

	/**
	 * Confirma la cuenta del usuario
	 */
	@Override
	public UserDTO confirmAccount(String token) {
		User user = userRepo.findUserByConfirmationTokenAndEnabled(token, false)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Confirmation token not found"));

		user.setEnabled(true);
		user.setConfirmationToken(null);
		
		userRepo.save(user);

		return userConverter.userToUserDTO(user);
	}

	/**
	 * Inicia sesion con google
	 * 
	 * @throws IOException
	 */
	@Override
	public void loginGoogle(String token, HttpServletResponse response) throws IOException {
		// Verifica el token
		GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance()).setAudience(Collections.singletonList(GOOGLE_CLIENTID));

		// Obtiene el token de la id y de este consigue el payload
		GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);
		GoogleIdToken.Payload payload = googleIdToken.getPayload();

		if (!payload.getEmailVerified()) {
			throw new ResponseStatusException(FORBIDDEN, "Email not verified");
		}

		// Obtiene el usuario por el email y si está activo
		User user = userRepo.getUserByEmail(payload.getEmail())
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));

		// Obtiene el userDTO
		UserDTO userDTO = getUser(userConverter.userToUserDTO(user));

		// Le añade el token del usuario
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + generateToken(user, userDTO));
	}

}