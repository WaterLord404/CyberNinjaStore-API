package com.cyberninja.security.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.security.model.entity.dto.UserDTO;
import com.cyberninja.security.services.UserServiceI;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired private UserServiceI userService;

	@PostMapping("/sign-up")
	public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO dto) {
		try {
			return ResponseEntity.ok(userService.createUser(dto));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(exposedHeaders = "Authorization")
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/confirm-account")
	public ResponseEntity<UserDTO> confirmAccount(@RequestParam String token) {
		try {
			return ResponseEntity.ok(userService.confirmAccount(token));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin(exposedHeaders = "Authorization")
	@PostMapping("/login/google")
	public ResponseEntity<UserDTO> loginGoogle(
			@RequestBody String token, 
			HttpServletResponse response
			) {
		try {
			userService.loginGoogle(token, response);
			return ResponseEntity.ok().build();

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
}
