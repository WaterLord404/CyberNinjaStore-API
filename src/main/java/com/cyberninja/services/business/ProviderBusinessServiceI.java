package com.cyberninja.services.business;

import java.util.List;

import com.cyberninja.model.entity.OrderDetails;

public interface ProviderBusinessServiceI {

	void calculateProfits(List<OrderDetails> ordersDetails);

}
