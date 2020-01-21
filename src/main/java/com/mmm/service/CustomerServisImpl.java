package com.mmm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmm.customer.CustomerAddress;
import com.mmm.customer.CustomerDetails;
import com.mmm.dao.CustomerDAO;

@Service
public class CustomerServisImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDao;

	@Override
	@Transactional
	public List<CustomerDetails> getCustomers() {
		return customerDao.getCustomers();
	}

	@Override
	@Transactional
	public void addCustomer(CustomerDetails customer) {
		customerDao.addCustomer(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerDao.deleteCustomer(id);
	}

	@Override
	@Transactional
	public CustomerDetails getCustomer(int theId) {

		return customerDao.getCustomer(theId);

	}
	
	@Override
	@Transactional
	public CustomerAddress getCustomerAddress(int theId) {
		
		return customerDao.getCustomerAddress(theId);
	}

	@Override
	@Transactional
	public List<CustomerDetails> getCustomerbyName(String name) {

		return customerDao.getCustomerbyName(name);

	}
}
