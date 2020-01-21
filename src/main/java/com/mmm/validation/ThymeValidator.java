package com.mmm.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mmm.usercrm.CRMuser;

public class ThymeValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		
		return CRMuser.class.equals(arg0) ;
	}

	@Override
	public void validate(Object user, Errors e) {
	
		CRMuser crmUser = (CRMuser) user;
		
		if(!crmUser.getPassword().equals(crmUser.getMatchingPassword())){
			
			e.rejectValue("matchingPassword", "PasswordsDontMatch");
		}
	}
}
