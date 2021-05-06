package com.cyberninja.services.entity.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.converter.OrderConverter;
import com.cyberninja.model.entity.converter.OrderDetailsConverter;
import com.cyberninja.model.entity.dto.OrderDTO;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.model.repository.CustomerRepository;
import com.cyberninja.model.repository.OrderDetailsRepository;
import com.cyberninja.model.repository.OrderRepository;
import com.cyberninja.services.business.OrderBusinessServiceI;
import com.cyberninja.services.entity.CouponServiceI;
import com.cyberninja.services.entity.CustomerServiceI;
import com.cyberninja.services.entity.OrderServiceI;
import com.cyberninja.services.entity.ProductServiceI;
import com.cyberninja.services.entity.ShippingServiceI;

@Service
public class OrderServiceImpl implements OrderServiceI {

	@Autowired private CustomerRepository customerRepo;

	@Autowired private OrderDetailsConverter orderDetailsConverter;

	@Autowired private OrderConverter orderConverter;

	@Autowired private ProductServiceI productService;

	@Autowired private OrderDetailsRepository orderDetailsRepo;

	@Autowired private OrderBusinessServiceI orderBService;

	@Autowired private CouponServiceI couponService;

	@Autowired private CustomerServiceI customerService;
	
	@Autowired private OrderRepository orderRepo;
	
	@Autowired private ShippingServiceI shippingService;
	
	/**
	 * Añade un pedido a su correspondiente customer
	 * 
	 * @return OrderDTO
	 */
	@Override
	public OrderDTO purchaseOrder(List<OrderDetailsDTO> dtos, Authentication auth, String couponCode) {
		Order order = getOrderOrCreate(auth);

		List<OrderDetails> ordersDetails = orderDetailsConverter.orderDetailsDTOToOrderDetails(dtos);
		
		// Elimina los orderDetails del usuario
		orderDetailsRepo.deleteAll(orderDetailsRepo.findUserProductsCart(Long.parseLong(auth.getName())));
		
		// Busca, actualiza fecha compra y asigna el customer
		Customer customer = customerRepo.findById(Long.valueOf(auth.getName())).get();
		customer.setLastPurchase(LocalDateTime.now());
		order.setCustomer(customer);

		// Asigna el cupon
		if(couponCode != null) {
			couponCode = couponCode.toUpperCase();
			order.setCoupon(couponService.getValidCouponByCode(couponCode));			
		}
		
		// Asigna a cada order detail su order, product y carrito
		for (OrderDetails i : ordersDetails) {
			i.setOrder(order);
			// Obtiene el producto con el id lista de orderDetailsDTO
			i.setProduct(productService.getProduct(
									dtos.get(ordersDetails.indexOf(i))
									.getProduct().getId()));
		}

		// Calcula el precio total
		order.setTotalPrice(orderBService.calculateTotalPrice(ordersDetails, order.getCoupon()));
		order.setPurchaseDate(LocalDateTime.now());
		
		// Crea el envio
		shippingService.addShipping(order);
		
		orderDetailsRepo.saveAll(ordersDetails);

		return orderConverter.orderToOrderDTO(order);
	}
	
	@Override
	public Order createOrder(Authentication auth) {
		Order order = new Order();
		order.setCustomer(customerService.getCustomerById(Long.parseLong(auth.getName())));
		return order;
	}
	
	/**
	 * Obtiene una order, si no existe la crea
	 */
	@Override
	public Order getOrderOrCreate(Authentication auth) {
		return orderRepo.getUserOrder(Long.parseLong(auth.getName()))
				.orElse(createOrder(auth));
	}


}
