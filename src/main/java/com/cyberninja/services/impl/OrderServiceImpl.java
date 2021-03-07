package com.cyberninja.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.converter.OrderDTOConverter;
import com.cyberninja.model.entity.converter.OrderDetailsDTOConverter;
import com.cyberninja.model.entity.dto.OrderDTO;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.model.repository.CustomerRepository;
import com.cyberninja.model.repository.OrderDetailsRepository;
import com.cyberninja.services.OrderServiceI;
import com.cyberninja.services.ProductServiceI;

@Service
public class OrderServiceImpl implements OrderServiceI {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrderDetailsDTOConverter orderDetailsConverter;

	@Autowired
	private OrderDTOConverter orderConverter;

	@Autowired
	private ProductServiceI productService;

	@Autowired
	private OrderDetailsRepository orderDetailsRepo;

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
	 * Añade un pedido a su correspondiente customer
	 * 
	 * @return OrderDTO
	 */
	@Override
	public OrderDTO purchaseOrder(List<OrderDetailsDTO> dtos, Authentication auth) {
		List<OrderDetails> ordersDetails = orderDetailsConverter.orderDetailsDTOToOrderDetails(dtos);

		Order order = orderConverter.orderDTOToOrder(new OrderDTO());
		// Busca y asigna el customer
		order.setCustomer(customerRepo.findById(Long.valueOf(auth.getName())).get());

		// Setea el producto y order
		for (OrderDetails orderDetails : ordersDetails) {
			orderDetails.setOrder(order);
			orderDetails.setProduct(productService.getProduct(
									dtos.get(ordersDetails.indexOf(orderDetails))
									.getProduct().getId()));
		}

		// Calcula el precio total
		order.setTotalPrice(calculateTotalPrice(ordersDetails));

		orderDetailsRepo.saveAll(ordersDetails);

		return orderConverter.orderToOrderDTO(order);
	}

	/**
	 * Suma el precio de todos los productos
	 * 
	 * @param products
	 * @return
	 */
	private Double calculateTotalPrice(List<OrderDetails> ordersDetails) {
		Double result = 0.0;
		for (OrderDetails orderDetails : ordersDetails) {
			result = result + (orderDetails.getUnits() * orderDetails.getProduct().getTotalPrice());
		}
		return Math.round(result * 100.0) / 100.0;
	}

}
