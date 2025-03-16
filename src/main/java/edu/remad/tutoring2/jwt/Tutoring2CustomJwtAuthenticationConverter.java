package edu.remad.tutoring2.jwt;

import static edu.remad.tutoring2.appconstants.JwtAppConstants.JWT_CLAIM_RESSOURCE_ACCESS;
import static edu.remad.tutoring2.appconstants.JwtAppConstants.JWT_CONVERTER_PRINCIPAL_ATTRIBUTE;
import static edu.remad.tutoring2.appconstants.JwtAppConstants.JWT_CONVERTER_RESOURCE_ID;
import static edu.remad.tutoring2.appconstants.JwtAppConstants.JWT_ROLES_KEY;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * Converts roles from Keycloak to Spring Security roles. It reads JWT and fetches all claims and roles as roles. 
 */
@Component
public class Tutoring2CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

	/**
	 * Default Constructor
	 */
	public Tutoring2CustomJwtAuthenticationConverter() {
		jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	}

	@Override
	public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
		Collection<GrantedAuthority> authorities = Stream
				.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractJwtResourceRoles(jwt).stream())
				.collect(Collectors.toSet());

		return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
	}

	private Collection<? extends GrantedAuthority> extractJwtResourceRoles(Jwt jwt) {
		if (jwt.getClaimAsMap(JWT_CLAIM_RESSOURCE_ACCESS) == null) {
			return Set.of();
		}

		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
		if (resourceAccess.get(JWT_CONVERTER_RESOURCE_ID) == null) {
			return Set.of();
		}

		if (resourceAccess.get(JWT_CONVERTER_RESOURCE_ID) == null) {
			return Set.of();
		}

		Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(JWT_CONVERTER_RESOURCE_ID);
		Collection<String> resourceRoles = (Collection<String>) resource.get(JWT_ROLES_KEY);

		return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toSet());
	}

	private String getPrincipalClaimName(Jwt jwt) {
		String claimName = JwtClaimNames.SUB;
		
		if (JWT_CONVERTER_PRINCIPAL_ATTRIBUTE != null) {
			claimName = JWT_CONVERTER_PRINCIPAL_ATTRIBUTE;
		}

		return jwt.getClaim(claimName);
	}

}
