package com.mmm.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mmm.service.UserService;
import com.mmm.userEntity.User;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
		private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	 	@Autowired
	    private UserService userService;
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			
			String targetUrl = determineTargetUrl(authentication);

			String userName = authentication.getName();

			User theUser = userService.findByUserName(userName);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("user", theUser);
			
			if (response.isCommitted()) {
	            System.out.println("Can't redirect");
	            return;
	        }
	 
	        redirectStrategy.sendRedirect(request, response, targetUrl);
			
		} 

		protected String determineTargetUrl(Authentication authentication) {
	        String url = "";
	 
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	 
	        List<String> roles = new ArrayList<String>();
	 
	        for (GrantedAuthority a : authorities) {
	            roles.add(a.getAuthority());
	        }
	 
	        if (isManager(roles)) {
	            url = "/showAllCustomers";
	        } else if (isAdmin(roles)) {
	            url = "/showAllCustomers";
	        } else if (isEmployee(roles)) {
	            url = "/showAllCustomers";
	        } else {
	            url = "/accessDenied";
	        }
	 
	        return url;
	    }
	 
	    private boolean isEmployee(List<String> roles) {
	        if (roles.contains("ROLE_EMPLOYEE")) {
	            return true;
	        }
	        return false;
	    }
	 
	    private boolean isAdmin(List<String> roles) {
	        if (roles.contains("ROLE_ADMIN")) {
	            return true;
	        }
	        return false;
	    }
	 
	    private boolean isManager(List<String> roles) {
	        if (roles.contains("ROLE_MANAGER")) {
	            return true;
	        }
	        return false;
	    }
	 
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }
	 
	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }		
}
