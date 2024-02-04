package edu.remad.tutoring2.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.remad.tutoring2.security.interfaces.IAuthenticationFacade;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public String getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getName();
	}

	@Override
	public boolean isAuthentoicated() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}

	@Override
	public Object getDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getDetails();
	}

	@Override
	public Object getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getPrincipal();
	}

	@Override
	public Object getCredentials() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getCredentials();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getAuthorities();
	}
}
