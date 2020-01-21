package com.mmm.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mmm.userEntity.User;
import com.mmm.usercrm.CRMuser;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);

	void addNewUser(CRMuser crmUserEntity);

	List<User> showUsers();

	void deleteUser(int userID);
	
}
