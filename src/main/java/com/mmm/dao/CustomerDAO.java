package com.mmm.dao;

import java.util.List;

import com.mmm.customer.CustomerAddress;
import com.mmm.customer.CustomerDetails;

public interface CustomerDAO {
	
public List<CustomerDetails> getCustomers();
	
	public void addCustomer(CustomerDetails customer);
	
	public void deleteCustomer(int id);
	
	public CustomerDetails getCustomer(int id);
	
	public List<CustomerDetails> getCustomerbyName(String name);

	public CustomerAddress getCustomerAddress(int theId);

}
