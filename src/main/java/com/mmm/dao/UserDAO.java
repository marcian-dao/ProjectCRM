package com.mmm.dao;

import java.util.List;

import com.mmm.userEntity.User;

public interface UserDAO {
	
	User findByUserName(String userName);

	void addNewUser(User user);

	List<User> showUsers();

	void deleteUser(int userID);

}
