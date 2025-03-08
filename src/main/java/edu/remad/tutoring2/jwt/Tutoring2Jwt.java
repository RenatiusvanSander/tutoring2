package edu.remad.tutoring2.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class Tutoring2Jwt extends JwtAuthenticationToken {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5384041248247392229L;

	private String firstName;
	
	private String lastName;
	
	public Tutoring2Jwt(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
		super(jwt, authorities);
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

}
