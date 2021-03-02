package com.cyberninja.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.converter.OrderDTOConverter;
import com.cyberninja.model.entity.dto.OrderDTO;
import com.cyberninja.model.repository.CustomerRepository;
import com.cyberninja.model.repository.OrderRepository;
import com.cyberninja.services.OrderServiceI;
import com.cyberninja.services.ProductServiceI;

@Service
public class OrderServiceImpl implements OrderServiceI{

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private OrderDTOConverter orderConverter;
	
	@Autowired
	private ProductServiceI productService;
	
	/**
	 * Añade un pedido a su correspondiente customer con sus productos
	 * @return OrderDTO
	 */
	@Override
	public OrderDTO purchaseOrder(OrderDTO dto, Authentication auth) {
		Order order = orderConverter.orderDTOToOrder(dto);
		// Busca y asigna el customer
		order.setCustomer(customerRepo.findById(Long.valueOf(auth.getName())).get());
		
		List<Product> products = productService.findSelectedProducts(dto.getProducts());
		
		order.setTotalPrice(calculateTotalPrice(products));
		order.setProducts(products);

		return orderConverter.orderToOrderDTO(orderRepo.save(order));
	}
	
	/**
	 * Suma el precio de todos los productos
	 * @param products
	 * @return
	 */
	private Double calculateTotalPrice(List<Product> products) {
		Double result = 0.0;
		for (Product product : products) {
			result = result + product.getTotalPrice();
		}
		return result;
	}
	

	

}
