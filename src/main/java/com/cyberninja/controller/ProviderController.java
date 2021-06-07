package com.cyberninja.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.cyberninja.model.entity.dto.ProviderDTO;
import com.cyberninja.services.entity.ProviderServiceI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/provider")
public class ProviderController {
	
	@Autowired private ProviderServiceI providerService;
	
	@GetMapping(path = "/{idProduct}")
	public ResponseEntity<ProviderDTO> getProviderOfProduct(@PathVariable Long idProduct) {
		try {
			return ResponseEntity.ok(providerService.getProviderOfProduct(idProduct));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/{idProvider}/{idProduct}")
	public ResponseEntity<ProviderDTO> setProviderToProduct(@PathVariable Long idProvider, @PathVariable Long idProduct) {
		try {
			return ResponseEntity.ok(providerService.setProviderToProduct(idProvider, idProduct));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ProviderDTO> addProvider(@RequestBody ProviderDTO dto) {
		try {
			return ResponseEntity.ok(providerService.addProvider(dto));

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(e.getStatus(), e.getReason());
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new ResponseStatusException(BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(CONFLICT);
		} catch (Exception e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
	}
}
