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
	public List<CustomerDetails> getAllCustomers() {
		return customerDao.getAllCustomers();
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
	public CustomerDetails getCustomerById(int theId) {

		return customerDao.getCustomerById(theId);

	}
	
	@Override
	@Transactional
	public CustomerAddress getCustomerAddress(int theId) {
		
		return customerDao.getCustomerAddress(theId);
	}

	@Override
	@Transactional
	public List<CustomerDetails> getCustomerByName(String name) {

		return customerDao.getCustomerByName(name);

	}
}
