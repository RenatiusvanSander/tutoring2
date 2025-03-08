package edu.remad.tutoring2.security.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.proc.JWSAlgorithmFamilyJWSKeySelector;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

@Configuration
@EnableMethodSecurity
public class Oauth2ResourcServerConfig {

	private String keySetUri = "https://192.168.120.59:8443/realms/ConnectTrial/protocol/openid-connect/certs";

	@Bean
	public JwtDecoder jwtDecoder() throws KeySourceException, MalformedURLException {
		JWSKeySelector<SecurityContext> jwsKeySelector =
	            JWSAlgorithmFamilyJWSKeySelector.fromJWKSetURL(new URL("http://192.168.120.59:8080/realms/ConnectTrial/protocol/openid-connect/certs"));

	    DefaultJWTProcessor<SecurityContext> jwtProcessor =
	            new DefaultJWTProcessor<>();
	    jwtProcessor.setJWSKeySelector(jwsKeySelector);

	    return new NimbusJwtDecoder(jwtProcessor);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated();
		
		http.oauth2ResourceServer().jwt();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		return http.build();
	}
	
//	@Bean
//	@Order(2)
//	public SecurityFilterChain ouath2ResourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.oauth2ResourceServer(c -> c.jwt(j -> j.jwkSetUri(keySetUri)));
//
//		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
//
//		return http.build();
//	}

}
