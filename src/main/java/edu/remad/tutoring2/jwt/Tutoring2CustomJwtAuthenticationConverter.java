package edu.remad.tutoring2.jwt;

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

@Component
public class Tutoring2CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

	private final String principalAttribute = "preferred_username";

	private final String resourceId = "tutoring2-resource-server";

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
		if (jwt.getClaimAsBoolean("resource_access") == null) {
			return Set.of();
		}

		Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
		if (resourceAccess.get(resourceId) == null) {
			return Set.of();
		}

		if (resourceAccess.get(resourceId) == null) {
			return Set.of();
		}

		Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(resourceId);
		Collection<String> resourceRoles = (Collection<String>) resource.get("roles");

		return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("Role_" + role))
				.collect(Collectors.toSet());
	}

	private String getPrincipalClaimName(Jwt jwt) {
		String claimName = JwtClaimNames.SUB;
		if (principalAttribute != null) {
			claimName = principalAttribute;
		}

		return jwt.getClaim(claimName);
	}

}
