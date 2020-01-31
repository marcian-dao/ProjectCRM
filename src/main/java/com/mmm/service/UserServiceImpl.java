package com.mmm.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmm.dao.UserDAO;
import com.mmm.userEntity.Role;
import com.mmm.userEntity.User;
import com.mmm.usercrm.CRMuser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public User findByUserName(String userName) {

		return userDAO.findByUserName(userName);
	}
	
	@Override
	@Transactional
	public void addNewUser(CRMuser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
						
		Role theRole = new Role();
		
		theRole.setName(crmUser.getUserRole());
		
		Set<Role> userRoles = new HashSet<>();
		
		userRoles.add(theRole);
		
		user.setRoles(userRoles);		

		userDAO.addNewUser(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDAO.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<User> showUsers() {
		
		List<User>showUsers = userDAO.showUsers();
		
		return showUsers;
	}

	@Override
	@Transactional
	public void deleteUser(int userID) {
		
		userDAO.deleteUser(userID);
		
	}
}
