package com.cyberninja.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.converter.OrderDTOConverter;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.model.entity.dto.ProductDTO;
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
	 * Obtiene los productos (activos e inactivos) seleccionados del carrito con las imagenes
	 * 
	 * Esto sirve, para eliminar un producto que mantenga el usuario en localstorage
	 * pero en BD no exista
	 * 	
	 * @return List ProductDTO activo/inactivo
	 */
	@Override
	public List<OrderDetailsDTO> getProductCart(List<OrderDetailsDTO> dtos) {
		for (OrderDetailsDTO orderDetailDTO : dtos) {
			dtos.get(dtos.indexOf(orderDetailDTO)).setProduct(
					productService.getProduct(orderDetailDTO.getProduct().getId()));
		}
		
		return dtos;
	}
	
//	/**
//	 * Añade un pedido a su correspondiente customer con sus productos
//	 * @return OrderDTO
//	 */
//	@Override
//	public OrderDTO purchaseOrder(OrderDTO dto, Authentication auth) {
//		Order order = orderConverter.orderDTOToOrder(dto);
//		// Busca y asigna el customer
//		order.setCustomer(customerRepo.findById(Long.valueOf(auth.getName())).get());
//		
//		// Obtiene los productos seleccionados
//		for (OrderProductDTO orderProductDTO : dto.getOrderProducts()) {
//			productService.getProduct(orderProductDTO.getProduct().getId());
//		}
//		
//		List<OrderProductDTO> products = productService.findSelectedProducts(dto.getOrderProducts());
//		
//		order.setTotalPrice(calculateTotalPrice(products));
//		order.setProducts(products);
//
//		return orderConverter.orderToOrderDTO(orderRepo.save(order));
//	}
		
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
		return Math.round(result * 100.0) / 100.0;
	}	

}
