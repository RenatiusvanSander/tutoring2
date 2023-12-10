package edu.remad.tutoring2.security.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.remad.tutoring2.appconstants.CORSAppConstants;

@Configuration
public class CorsSecurityConfig {
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList(CORSAppConstants.WILDCARDS));
	    configuration.setAllowedMethods(Arrays.asList(CORSAppConstants.WILDCARDS));
	    configuration.setAllowedHeaders(Arrays.asList(CORSAppConstants.WILDCARDS));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration(CORSAppConstants.PATH_WILDCARDS, configuration);
	    
	    return source;
	}
}
