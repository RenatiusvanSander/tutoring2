package edu.remad.tutoring2.security.interfaces;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public interface IAuthenticationFacade {

	Authentication getAuthentication();

	String getAuthenticatedUser();
	
	boolean isAuthentoicated();
	
	Object getDetails();
	
	Object getPrincipal();
	
	Object getCredentials();
	
	Collection<? extends GrantedAuthority> getAuthorities();
}
