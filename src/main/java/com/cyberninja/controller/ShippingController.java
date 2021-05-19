package com.cyberninja.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.dto.ShippingDTO;
import com.cyberninja.services.entity.ShippingServiceI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/shipping")
public class ShippingController {

	@Autowired
	private ShippingServiceI shippingService;

	@PostMapping(path = "/{shippingId}")
	public ResponseEntity<ShippingDTO> updateShipping(
			Authentication auth, 
			@PathVariable Long shippingId,
			@RequestBody ShippingDTO dto) {
		try {
			return ResponseEntity.ok(shippingService.updateShipping(auth, shippingId, dto));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<List<ShippingDTO>> updateShippings(Authentication auth, @RequestBody ShippingDTO dto) {
		try {
			return ResponseEntity.ok(shippingService.updateShippings(auth, dto));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
}
