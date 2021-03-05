package com.cyberninja.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.dto.OrderDTO;
import com.cyberninja.services.OrderServiceI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/cart")
public class OrderController {

	@Autowired
	private OrderServiceI orderService;

	@PostMapping(path = "/buy")
	public ResponseEntity<OrderDTO> purchaseOrder(@RequestBody OrderDTO dto, Authentication auth) {
		try {
			return ResponseEntity.ok(orderService.purchaseOrder(dto, auth));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}

}
