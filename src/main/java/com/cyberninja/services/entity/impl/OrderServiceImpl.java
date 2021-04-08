package com.cyberninja.services.entity.impl;

import java.time.LocalDate;
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
import com.cyberninja.services.business.OrderBusinessServiceI;
import com.cyberninja.services.entity.CouponServiceI;
import com.cyberninja.services.entity.OrderServiceI;
import com.cyberninja.services.entity.ProductServiceI;

@Service
public class OrderServiceImpl implements OrderServiceI {

	@Autowired private CustomerRepository customerRepo;

	@Autowired private OrderDetailsConverter orderDetailsConverter;

	@Autowired private OrderConverter orderConverter;

	@Autowired private ProductServiceI productService;

	@Autowired private OrderDetailsRepository orderDetailsRepo;

	@Autowired private OrderBusinessServiceI orderBService;

	@Autowired private CouponServiceI couponService;

	/**
	 * Añade un pedido a su correspondiente customer
	 * 
	 * @return OrderDTO
	 */
	@Override
	public OrderDTO purchaseOrder(List<OrderDetailsDTO> dtos, Authentication auth, String couponCode) {
		Order order = orderConverter.orderDTOToOrder(new OrderDTO());

		List<OrderDetails> ordersDetails = orderDetailsConverter.orderDetailsDTOToOrderDetails(dtos);
		
		// Busca, actualiza fecha compra y asigna el customer
		Customer customer = customerRepo.findById(Long.valueOf(auth.getName())).get();
		customer.setLastPurchase(LocalDate.now());
		order.setCustomer(customer);

		// Asigna el cupon
		if(couponCode != null) {
			couponCode = couponCode.toUpperCase();
			order.setCoupon(couponService.getValidCouponByCode(couponCode));			
		}
		
		// Asigna a cada order detail su order y product
		for (OrderDetails i : ordersDetails) {
			i.setOrder(order);
			// Obtiene el producto con el id lista de orderDetailsDTO
			i.setProduct(productService.getProduct(
									dtos.get(ordersDetails.indexOf(i))
									.getProduct().getId()));
		}

		// Calcula el precio total
		order.setTotalPrice(orderBService.calculateTotalPrice(ordersDetails, order.getCoupon()));

		orderDetailsRepo.saveAll(ordersDetails);

		return orderConverter.orderToOrderDTO(order);
	}

}
