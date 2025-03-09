package edu.remad.tutoring2.security.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.proc.JWSAlgorithmFamilyJWSKeySelector;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

@Configuration
public class Oauth2ResourcServerConfig {

	private String keySetUri = "http://192.168.120.59:8080/realms/ConnectTrial/protocol/openid-connect/certs";

    @Bean
    JwtDecoder jwtDecoder() throws KeySourceException, MalformedURLException {
		JWSKeySelector<SecurityContext> jwsKeySelector =
	            JWSAlgorithmFamilyJWSKeySelector.fromJWKSetURL(new URL(keySetUri));

	    DefaultJWTProcessor<SecurityContext> jwtProcessor =
	            new DefaultJWTProcessor<>();
	    jwtProcessor.setJWSKeySelector(jwsKeySelector);

	    return new NimbusJwtDecoder(jwtProcessor);
	}

}
