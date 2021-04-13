package com.cyberninja.services.entity.impl; 

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Cart;
import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.converter.OrderDetailsConverter;
import com.cyberninja.model.entity.converter.ProductConverter;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.model.repository.CartRepository;
import com.cyberninja.model.repository.OrderDetailsRepository;
import com.cyberninja.services.entity.CartServiceI;
import com.cyberninja.services.entity.ProductServiceI;

@Service
public class CartServiceImpl implements CartServiceI {

	@Autowired private CartRepository cartRepo;
	
	@Autowired private ProductServiceI productService;
			
	@Autowired private OrderDetailsConverter orderDetailsConverter;
		
	@Autowired private ProductConverter productConverter;
	
	@Autowired private OrderDetailsRepository orderDetailsRepo;
	
	/**
	 * Obtiene los productos del carrito que se guardará en el localstorage
	 */
	@Override
	public List<OrderDetailsDTO> getCartProducts(Authentication auth) {
		List<OrderDetailsDTO> dtos = new ArrayList<>(); 

		Cart cart = getCart(auth);

		// Items del carrito del usuario
		List<OrderDetails> ordersDetails = orderDetailsRepo.userCart(cart.getId());

		dtos = orderDetailsConverter.orderDetailsToOrderDetailsDTO(ordersDetails);
				
		// Asigna el producto a cada orderDetail
		for (OrderDetails i : ordersDetails) {
			dtos.get(ordersDetails.indexOf(i)).setProduct(
					productConverter.productToProductDTO(
							productService.getProduct(i.getProduct().getId())));
		}

		if (dtos.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND);
		}

		return dtos;
	}
	
	/**
	 * Guarda el carrito unicamente con los orderDetails nuevos
	 */
	@Override
	public List<OrderDetailsDTO> saveCart(List<OrderDetailsDTO> dtos, Authentication auth) {
		List<OrderDetails> ordersDetails = orderDetailsConverter.orderDetailsDTOToOrderDetails(dtos);
	
		Cart cart = getCart(auth);
		
		// Asigna el producto y el carrito a cada orderDetail
		for (OrderDetails i : ordersDetails) {
			i.setProduct(productService.getProduct(
					dtos.get(ordersDetails.indexOf(i))
					.getProduct().getId()));
			
			i.setCart(cart);
		}
		
		// Elimina los orderDetails del usuario para intercambiarlos con los nuevos
		orderDetailsRepo.deleteAll(orderDetailsRepo.userCart(cart.getId()));
		
		if (dtos.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND);
		}	
				
		orderDetailsRepo.saveAll(ordersDetails);

		return orderDetailsConverter.orderDetailsToOrderDetailsDTO(ordersDetails);
	}

	
	/**
	 * Obtiene los productos (activos e inactivos) seleccionados del carrito con las
	 * imagenes
	 * 
	 * Esto sirve, para eliminar un producto que mantenga el usuario en localstorage
	 * pero en BD no exista
	 * 
	 * @return List ProductDTO activo/inactivo
	 */
	@Override
	public List<OrderDetailsDTO> getProductCart(List<OrderDetailsDTO> dtos) {
		for (OrderDetailsDTO orderDetailDTO : dtos) {
			dtos.get(dtos.indexOf(orderDetailDTO))
					.setProduct(productService.getProductDTO(orderDetailDTO.getProduct().getId()));
		}

		return dtos;
	}
	
	/*
	 * Crea un carrito a un customer
	 */
	@Override
	public void createCart(Customer customer) {
		Cart cart = new Cart();
		
		cart.setCustomer(customer);
		customer.setCart(cart);
		
		cartRepo.save(cart);
	}

	/**
	 * Obtiene el carrito de un customer
	 */
	@Override
	public Cart getCart(Authentication auth) {
		Cart cart = cartRepo.getCart(Long.valueOf(auth.getName()))
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
		cart.setUpdateDate(LocalDate.now());
		return cart;
	}

}
