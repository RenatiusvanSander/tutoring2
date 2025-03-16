package edu.remad.tutoring2.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Transient
public class Tutoring2Jwt extends JwtAuthenticationToken {
	
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -5384041248247392229L;

	private String firstName;
	
	private String lastName;
	
	private SecurityContext securityContext;
	
	/**
	 * Constructs a {@code Tutoring2Jwt} using the provided parameters.
	 * @param jwt the JWT
	 */
	public Tutoring2Jwt(Jwt jwt) {
		super(jwt);
		securityContext = SecurityContextHolder.getContext();
	}

	/**
	 * Constructs a {@code Tutoring2Jwt} using the provided parameters.
	 * @param jwt the JWT
	 * @param authorities the authorities assigned to the JWT
	 */
	public Tutoring2Jwt(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
		super(jwt, authorities);
		setAuthenticated(true);
		securityContext = SecurityContextHolder.getContext();
	}

	/**
	 * Constructs a {@code Tutoring2Jwt} using the provided parameters.
	 * @param jwt the JWT
	 * @param authorities the authorities assigned to the JWT
	 * @param name the principal name
	 */
	public Tutoring2Jwt(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String name) {
		super(jwt, authorities, name);
		setAuthenticated(true);
		securityContext = SecurityContextHolder.getContext();
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
	
	public Map<String, Object> getClaims() {
		return new HashMap<>(super.getToken().getClaims());
	}
	
	public Map<String, Object> getHeaders() {
		return new HashMap<>(super.getToken().getHeaders());
	}
	
	public String getClaimsOfName( String claimName) {
		return (String) super.getToken().getClaims().getOrDefault(claimName, "");
	}
	
	public boolean hasAuthority(String authorityName) {
		if(StringUtils.isBlank(authorityName)) {
			return false;
		}
		
		for(GrantedAuthority authority : getAuthorities()) {
			if(authority.getAuthority().equals(authorityName)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasAuthority(GrantedAuthority authority) {
		if(authority == null) {
			return false;
		}
		
		return hasAuthority(authority.getAuthority());
	}
	
	public boolean hasAuthorities(final Collection<GrantedAuthority> authorities) {
		if(authorities == null || authorities.isEmpty()) {
			return false;
		}
		
		return authorities.containsAll(authorities);
	}
	
	public boolean hasAuthorities(GrantedAuthority... authorities) {
		return hasAuthorities(Arrays.asList(authorities));
	}
	
	public SecurityContext getSecurityContext() {
		return securityContext;
	}

}
