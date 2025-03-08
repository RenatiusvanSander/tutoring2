package edu.remad.tutoring2.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class Tutoring2JwtConverter implements Converter<Jwt, Tutoring2Jwt> {

	@Override
	public Tutoring2Jwt convert(Jwt source) {
		List<GrantedAuthority> grandtedAuthorities = new ArrayList<>();
		Tutoring2Jwt tutorJwt = new Tutoring2Jwt(source, grandtedAuthorities);
		tutorJwt.setFirstName(source.getClaimAsString("given_name"));
		tutorJwt.setLastName(source.getClaimAsString("family_name"));
		
		return tutorJwt;
	}

}
