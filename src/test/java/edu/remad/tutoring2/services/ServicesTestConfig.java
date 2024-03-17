package edu.remad.tutoring2.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.remad.tutoring2.appconstants.PackagesAppConstants;

@Configuration
@ComponentScan(PackagesAppConstants.EDU_REMAD_TUTORING2 + ".services")
public class ServicesTestConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
