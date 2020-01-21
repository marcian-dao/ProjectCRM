package com.mmm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmm.userEntity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String theUserName) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void addNewUser(User theUser) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theUser);

	}

	@Override
	public List<User> showUsers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<User> usersList = currentSession.createQuery("from User order by lastName", User.class).getResultList();
		
		return usersList;
	}

	@Override
	public void deleteUser(int userID) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		User tempUser = currentSession.get(User.class, userID);
		
		currentSession.delete(tempUser);
		
	}
}
