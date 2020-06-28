package com.mmm.service;

import java.util.List;

import com.mmm.customer.CustomerAddress;
import com.mmm.customer.CustomerDetails;

public interface CustomerService {
	
	public List<CustomerDetails> getAllCustomers();
	
	public void addCustomer(CustomerDetails customer);
	
	public void deleteCustomer(int id);
	
	public CustomerDetails getCustomerById(int id);
	
	public List<CustomerDetails> getCustomerByName(String name);

	public CustomerAddress getCustomerAddress(int theId);
	
}
