package com.cyberninja.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.dto.DiscountDTO;
import com.cyberninja.model.entity.dto.ProductDTO;
import com.cyberninja.services.entity.DiscountServiceI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/discount")
public class DiscountController {

	@Autowired
	private DiscountServiceI discountService;

	@GetMapping
	public ResponseEntity<List<DiscountDTO>> getProducts() {
		try {
			return ResponseEntity.ok(discountService.getDiscounts());

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus());
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<DiscountDTO> addDiscount(@RequestBody DiscountDTO dto) {
		try {
			return ResponseEntity.ok(discountService.addDiscount(dto));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/{productId}/{discountId}")
	public ResponseEntity<ProductDTO> setDiscount(
			@PathVariable Long productId, 
			@PathVariable(required = false) Long discountId,
			@PathVariable(required = false) Boolean active
			) {
		try {
			return ResponseEntity.ok(discountService.updateDiscount(productId, discountId));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
}
