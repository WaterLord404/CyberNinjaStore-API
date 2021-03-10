package com.cyberninja.services.entity.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cyberninja.model.Customer;
import com.cyberninja.model.Order;
import com.cyberninja.model.OrderDetails;
import com.cyberninja.model.converter.OrderConverter;
import com.cyberninja.model.converter.OrderDetailsConverter;
import com.cyberninja.model.dto.OrderDTO;
import com.cyberninja.model.dto.OrderDetailsDTO;
import com.cyberninja.repository.CustomerRepository;
import com.cyberninja.repository.OrderDetailsRepository;
import com.cyberninja.services.entity.OrderServiceI;
import com.cyberninja.services.entity.ProductServiceI;

@Service
public class OrderServiceImpl implements OrderServiceI {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrderDetailsConverter orderDetailsConverter;

	@Autowired
	private OrderConverter orderConverter;

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
		
		// Busca, actualiza fecha compra y asigna el customer
		Customer customer = customerRepo.findById(Long.valueOf(auth.getName())).get();
		customer.setLastPurchase(LocalDate.now());
		order.setCustomer(customer);

		// Setea el producto y order a la lista vacia (ordersDetails)
		for (OrderDetails orderDetails : ordersDetails) {
			orderDetails.setOrder(order);
			// Obtiene el producto con el id lista de orderDetailsDTO
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
