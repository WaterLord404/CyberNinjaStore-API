package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Cart;
import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.Order;
import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.converter.OrderConverter;
import com.cyberninja.model.entity.converter.OrderDetailsConverter;
import com.cyberninja.model.entity.converter.ProductConverter;
import com.cyberninja.model.entity.converter.ShippingConverter;
import com.cyberninja.model.entity.dto.OrderDTO;
import com.cyberninja.model.entity.dto.OrderDetailsDTO;
import com.cyberninja.model.repository.CustomerRepository;
import com.cyberninja.model.repository.OrderDetailsRepository;
import com.cyberninja.model.repository.OrderRepository;
import com.cyberninja.services.business.OrderBusinessServiceI;
import com.cyberninja.services.business.ProviderBusinessServiceI;
import com.cyberninja.services.entity.CartServiceI;
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
	
	@Autowired private CartServiceI cartService;
	
	@Autowired private ShippingConverter shippingConverter;
	
	@Autowired private ProductConverter productConverter;
	
	@Autowired private ProviderBusinessServiceI providerBService;
	
	/**
	 * Añade un pedido a su correspondiente customer
	 * 
	 * @return OrderDTO
	 * @throws ParseException 
	 */
	@Override
	public OrderDTO purchaseOrder(List<OrderDetailsDTO> dtos, Authentication auth, String couponCode) throws ParseException {
		Order order = getOrderOrCreate(auth);
		Cart cart = cartService.getCartOrCreate(auth);
		
		List<OrderDetails> ordersDetails = orderDetailsConverter.orderDetailsDTOToOrderDetails(dtos);
		
		// Elimina los orderDetails del usuario
		orderDetailsRepo.deleteAll(orderDetailsRepo.findUserProductsCart(Long.parseLong(auth.getName())));
		
		// Busca, actualiza fecha compra y asigna el customer
		Customer customer = customerRepo.findById(Long.valueOf(auth.getName())).get();
		customer.setLastPurchase(LocalDate.now());
		order.setCustomer(customer);

		// Asigna el cupon
		if (couponCode != null) {
			couponCode = couponCode.toUpperCase();
			order.setCoupon(couponService.getValidCouponByCode(couponCode));
		}
		
		// Asigna a cada order detail su order, product
		for (OrderDetails i : ordersDetails) {
			i.setOrder(order);
			// Obtiene el producto con el id lista de orderDetailsDTO
			i.setProduct(productService.getProduct(
									dtos.get(ordersDetails.indexOf(i)).getProduct().getId()));
		}

		// Calcula el precio total
		order.setTotalPrice(orderBService.calculateTotalPrice(ordersDetails, order.getCoupon()));
		order.setPurchaseDate(LocalDate.now());
		
		// Asigna el carrito
		order.setCart(cart);
		
		// Crea el envio
		shippingService.addShipping(order);

		// Asigna el precio al proveedor
		providerBService.calculateProfits(ordersDetails);
		
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
		return orderRepo.getUserCartOrder(Long.parseLong(auth.getName()))
				.orElse(createOrder(auth));
	}

	/**
	 * Obtiene los pedidos del usuario con su envio y ordersDetails y productos
	 */
	@Override
	public List<OrderDTO> getOrdersWithShipping(Authentication auth) {
		List<Order> orders = orderRepo.getUserOrders(Long.parseLong(auth.getName()));
		
		if (orders.isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND);
		}
		
		List<OrderDTO> dtos = orderConverter.ordersToOrdersDTO(orders);
		

		for (OrderDTO i : dtos) {
			i.setShipping(shippingConverter.shippingToShippingDTO(
					orders.get(dtos.indexOf(i)).getShipping()));
			
			List<OrderDetails> ordersDetails = orderDetailsRepo.findUserProductsOnOrder(
																Long.parseLong(auth.getName()),
																i.getId());
			
			List<OrderDetailsDTO> ordersDetailsDTO = orderDetailsConverter.orderDetailsToOrderDetailsDTO(ordersDetails);
			
			for (OrderDetailsDTO j : ordersDetailsDTO) {
				j.setProduct(productConverter.productToProductDTO(
						ordersDetails.get(ordersDetailsDTO.indexOf(j)).getProduct()));
			}
			
			i.setOrdersDetails(ordersDetailsDTO);
			
			i.setPurchaseDate(orders.get(dtos.indexOf(i)).getPurchaseDate());
		}
		
		return dtos;
	}

}
