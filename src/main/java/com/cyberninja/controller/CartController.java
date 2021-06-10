package com.cyberninja.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.services.entity.CartServiceI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/cart")
public class CartController {

	@Autowired private CartServiceI cartService;
	
	@GetMapping
	public ResponseEntity<List<OrderDetailsDTO>> getCart(Authentication auth) {
		try {
			return ResponseEntity.ok(cartService.getCart(auth));
			
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<List<OrderDetailsDTO>> saveCart(@RequestBody List<OrderDetailsDTO> dtos, Authentication auth) {
		try {
			return ResponseEntity.ok(cartService.saveCart(dtos, auth));
			
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<List<OrderDetailsDTO>> verifyCart(@RequestBody List<OrderDetailsDTO> dtos) {
		try {
			return ResponseEntity.ok(cartService.verifyCart(dtos));
			
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
}
