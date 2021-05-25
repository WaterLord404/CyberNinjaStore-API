package com.cyberninja.model.entity.converter;

import org.springframework.stereotype.Component;

import com.cyberninja.model.entity.Customer;
import com.cyberninja.model.entity.dto.CustomerDTO;

@Component
public class CustomerConverter {

	public Customer customerDTOToCustomer(CustomerDTO dto) {
		Customer customer = new Customer();

		customer.setName(dto.getName());
		customer.setSurname(dto.getSurname());
		customer.setEmail(dto.getEmail());
		customer.setTelephone(dto.getTelephone());
		customer.setCounty(dto.getCounty());
		customer.setState(dto.getState());
		customer.setVillage(dto.getVillage());
		customer.setPostalCode(dto.getPostalCode());
		customer.setAddress(dto.getAddress());
		customer.setLastPurchase(null);
		
		return customer;
	}

	public CustomerDTO CustomerToCustomerDTO(Customer customer) {
		CustomerDTO dto = new CustomerDTO();

		dto.setName(customer.getName());
		dto.setSurname(customer.getSurname());
		dto.setEmail(customer.getEmail());
		dto.setTelephone(customer.getTelephone());
		dto.setCounty(customer.getCounty());
		dto.setState(customer.getState());
		dto.setVillage(customer.getVillage());
		dto.setPostalCode(customer.getPostalCode());
		dto.setAddress(customer.getAddress());

		return dto;
	}
}
