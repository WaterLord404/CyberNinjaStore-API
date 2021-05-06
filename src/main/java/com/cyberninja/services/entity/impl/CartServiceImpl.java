package com.cyberninja.services.entity.impl; 

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Cart;
import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.converter.OrderDetailsConverter;
import com.cyberninja.model.entity.converter.ProductConverter;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.model.repository.CartRepository;
import com.cyberninja.model.repository.OrderDetailsRepository;
import com.cyberninja.services.entity.CartServiceI;
import com.cyberninja.services.entity.OrderServiceI;
import com.cyberninja.services.entity.ProductServiceI;

@Service
public class CartServiceImpl implements CartServiceI {

	@Autowired private CartRepository cartRepo;
	
	@Autowired private ProductServiceI productService;
			
	@Autowired private OrderDetailsConverter orderDetailsConverter;
		
	@Autowired private ProductConverter productConverter;
	
	@Autowired private OrderDetailsRepository orderDetailsRepo;
	
	@Autowired private OrderServiceI orderService;
		
	/**
	 * Obtiene los productos del carrito que se guardará en el localstorage
	 */
	@Override
	public List<OrderDetailsDTO> getCartProducts(Authentication auth) {
		List<OrderDetailsDTO> dtos = new ArrayList<>(); 

		// Items del carrito del usuario
		List<OrderDetails> ordersDetails = orderDetailsRepo.findUserProductsCart(Long.parseLong(auth.getName()));

		dtos = orderDetailsConverter.orderDetailsToOrderDetailsDTO(ordersDetails);
				
		if (dtos.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND);
		}
		
		// Asigna el producto a cada orderDetail
		for (OrderDetails i : ordersDetails) {
			dtos.get(ordersDetails.indexOf(i)).setProduct(
					productConverter.productToProductDTO(
							productService.getProduct(i.getProduct().getId())));
		}

		return dtos;
	}
	
	/**
	 * Guarda el carrito unicamente con los orderDetails nuevos
	 */
	@Override
	public List<OrderDetailsDTO> saveCart(List<OrderDetailsDTO> dtos, Authentication auth) {
		List<OrderDetails> ordersDetails = orderDetailsConverter.orderDetailsDTOToOrderDetails(dtos);
	
		// Elimina los orderDetails del usuario
		orderDetailsRepo.deleteAll(orderDetailsRepo.findUserProductsCart(Long.parseLong(auth.getName())));

		if (dtos.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND);
		}

		// Busca el order, si no existe lo crea
		Order order = orderService.getOrderOrCreate(auth);
		
		// Cada producto asigna su order y producto
		for (OrderDetails i : ordersDetails) {
			i.setProduct(productService.getProduct(
					dtos.get(ordersDetails.indexOf(i))
					.getProduct().getId()));
			i.setOrder(order);
		}
		
		// asigna productos
		order.setOrdersDetails(ordersDetails);
				
		// asigna carrito
		Cart cart = getCartOrCreate(auth);
		cart.setUpdateDate(LocalDateTime.now());
		
		// Asigna el order y el cart
		cart.setOrder(order);
		order.setCart(cart);
		
		cartRepo.save(cart);

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
	
	/**
	 * Obtiene un carrito, y si no existe lo crea
	 */
	@Override
	public Cart getCartOrCreate(Authentication auth) {
		return cartRepo.findUserCart(Long.parseLong(auth.getName()))
				.orElse(new Cart());
	}

}
