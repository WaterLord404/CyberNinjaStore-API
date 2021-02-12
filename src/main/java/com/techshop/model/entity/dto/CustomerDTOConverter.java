package com.techshop.model.entity.dto;

import org.springframework.stereotype.Component;

import com.techshop.model.entity.Customer;

@Component
public class CustomerDTOConverter {

	/**
	 * Convierte CustomerDTO a Customer
	 * 
	 * @param dto
	 * @return Customer
	 */
	public Customer customerDTOToCustomer(CustomerDTO dto) {
		Customer customer = new Customer();

		customer.setName(dto.getName());
		customer.setSurname(dto.getSurname());
		customer.setEmail(dto.getEmail());
		customer.setTelephone(dto.getTelephone());
		customer.setRegion(dto.getRegion());
		customer.setProvince(dto.getProvince());
		customer.setLocality(dto.getLocality());
		customer.setPostalCode(dto.getPostalCode());
		customer.setAddress(dto.getAddress());
		customer.setLastPurchase(null);
	
		return customer;
	}

	/**
	 * Convierte Customer a CustomerDTO
	 * 
	 * @param customer
	 * @return CustomerDTO
	 */
	public CustomerDTO CustomerToCustomerDTO(Customer customer) {
		CustomerDTO dto = new CustomerDTO();

		dto.setName(customer.getName());
		dto.setSurname(customer.getSurname());
		dto.setEmail(customer.getEmail());
		dto.setTelephone(customer.getTelephone());
		dto.setRegion(customer.getRegion());
		dto.setProvince(customer.getProvince());
		dto.setLocality(customer.getLocality());
		dto.setPostalCode(customer.getPostalCode());
		dto.setAddress(customer.getAddress());

		return dto;
	}
}
