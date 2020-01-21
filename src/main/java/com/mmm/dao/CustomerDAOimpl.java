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

	@Override
	public List<CustomerDetails> getCustomers() {

		Session currentSession = sessionFactory.getCurrentSession();

		List<CustomerDetails> customersList = currentSession
				.createQuery("from CustomerDetails order by lastName", CustomerDetails.class).getResultList();

		return customersList;
	}

	@Override
	public void addCustomer(CustomerDetails customer) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int id) {

		Session currentSession = sessionFactory.getCurrentSession();

		CustomerDetails theCustomer = currentSession.get(CustomerDetails.class, id);

		currentSession.delete(theCustomer);

	}

	@Override
	public CustomerDetails getCustomer(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();

		CustomerDetails theCustomer = currentSession.get(CustomerDetails.class, theId);

		return theCustomer;

	}

	@Override
	public CustomerAddress getCustomerAddress(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();

		CustomerAddress theCustomerAddress = currentSession.get(CustomerAddress.class, theId);

		return theCustomerAddress;

	}

	@Override
	public List<CustomerDetails> getCustomerbyName(String name) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<CustomerDetails> theQuery = null;

		if (name != null && name.trim().length() > 0) {

			theQuery = currentSession.createQuery(
					"from CustomerDetails where lower(first_name) like :theName or lower(last_name) like :theName",
					CustomerDetails.class);
			theQuery.setParameter("theName", "%" + name.toLowerCase() + "%");

		} else {

			theQuery = currentSession.createQuery("from CustomerDetails order by lastName", CustomerDetails.class);

		}

		List<CustomerDetails> customers = theQuery.getResultList();

		return customers;
	}
}
