package com.mmm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmm.customer.CustomerAddress;
import com.mmm.customer.CustomerDetails;

@Repository
public class CustomerDAOimpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	public Session currentSession() {
		
		return sessionFactory.getCurrentSession();
	}


	@Override
	public List<CustomerDetails> getAllCustomers() {
	

		List<CustomerDetails> customersList = currentSession()
				.createQuery("from CustomerDetails order by lastName", CustomerDetails.class).getResultList();

		return customersList;
	}

	@Override
	public void addCustomer(CustomerDetails customer) {
	

		currentSession().saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int id) {
	

		CustomerDetails theCustomer = currentSession().get(CustomerDetails.class, id);

		currentSession().delete(theCustomer);

	}

	@Override
	public CustomerDetails getCustomerById(int theId) {
	

		CustomerDetails theCustomer = currentSession().get(CustomerDetails.class, theId);

		return theCustomer;

	}

	@Override
	public CustomerAddress getCustomerAddress(int theId) {	

		CustomerAddress theCustomerAddress = currentSession().get(CustomerAddress.class, theId);

		return theCustomerAddress;

	}

	@Override
	public List<CustomerDetails> getCustomerByName(String name) {
	

		Query<CustomerDetails> theQuery = null;

		if (name != null && name.trim().length() > 0) {

			theQuery = currentSession().createQuery(
					"from CustomerDetails where lower(first_name) like :theName or lower(last_name) like :theName",
					CustomerDetails.class);
			theQuery.setParameter("theName", "%" + name.toLowerCase() + "%");

		} else {

			theQuery = currentSession().createQuery("from CustomerDetails order by lastName", CustomerDetails.class);

		}

		List<CustomerDetails> customers = theQuery.getResultList();

		return customers;
	}
}
