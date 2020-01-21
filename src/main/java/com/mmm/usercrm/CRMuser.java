package com.mmm.usercrm;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.mmm.validation.FieldMatch;
import com.mmm.validation.ValidEmail;

@FieldMatch.List({
@FieldMatch(first = "password", second = "matchingPassword", message = "The passwords must match") })
@Component
public class CRMuser {

	@NotNull(message = "*Required")
	@Size(min = 2, message = " Min. 2 letters. ")
	private String userName;

	@NotNull(message = "*Required")
	@Size(min = 6, message = " Min. 6 letters. ")
	private String password;

	@NotNull(message = "*Required")
	@Size(min = 6, message = " Min. 6 letters. ")
	private String matchingPassword;

	@NotNull(message = "*Required")
	@Size(min = 2, message = " Min. 2 letters. ")
	private String firstName;

	@NotNull(message = "*Required")
	@Size(min = 2, message = " Min. 2 letters. ")
	private String lastName;

	@ValidEmail
	private String email;
	
	public CRMuser() {
		
		if(roles == null) {			
			roles = new HashMap<>();
		}
		
		roles.put("Employee","ROLE_EMPLOYEE");
		roles.put("Admin","ROLE_ADMIN");
		roles.put("Manager","ROLE_MANAGER");	
	}
	
	public Map<String, String> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}

	private Map<String,String> roles;
	
	private String userRole;

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
