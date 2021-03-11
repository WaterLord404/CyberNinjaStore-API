package com.cyberninja.services.business;

import java.util.List;

import com.cyberninja.model.entity.OrderDetails;
import com.cyberninja.model.entity.Product;

public interface InvoiceServiceI {

	public Product calculateInvoice(Product product);

	public Double calculateTotalPrice(List<OrderDetails> ordersDetails);
}
