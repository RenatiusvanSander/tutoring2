package edu.remad.tutoring2.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;

import com.mysql.cj.jdbc.Driver;

import edu.remad.tutoring2.appconstants.AppStageConstants;
import edu.remad.tutoring2.security.ContentSecurityPolicySettings;
import edu.remad.tutoring2.security.filters.TenantFilter;
import edu.remad.tutoring2.services.impl.CustomJpaUserDetailsService;
import edu.remad.tutoring2.systemenvironment.SystemEnvironment;

@Configuration
public class JdbcSecurityConfiguration {

	@Value("${spring.websecurity.debug:true}")
	boolean webSecurityDebug;

	private static final ClearSiteDataHeaderWriter.Directive[] COOKIES = Directive.values();

	@Autowired
	private ContentSecurityPolicySettings contentSecurityPolicies;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.debug(webSecurityDebug);
	}

	/**
	 * Does form login filter chain and has also http security.
	 * 
	 * @param http similar to spring security xml config for filtering request
	 * @return created security filter chain, {@link SecurityFilterChain}
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors();
		http.headers().xssProtection().and()
				.contentSecurityPolicy(contentSecurityPolicies.getContentSecurityPolicies());
		http.addFilterAfter(new TenantFilter(), BasicAuthenticationFilter.class)
				.securityContext((securityContext) -> securityContext.requireExplicitSave(true))
				.sessionManagement(
						session -> session.maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login"))
				.authorizeRequests().antMatchers("/", "/helloWorld", "/logoutSuccess", "/signup").permitAll()
				.antMatchers("/hello", "/bye", "/login", "/logout", "/templates/**").authenticated().and().formLogin()
				.loginPage("/myCustomLogin").loginProcessingUrl("/process-login").defaultSuccessUrl("/hello", true)
//        .failureUrl("/login.html?error=true")
//        .failureHandler(authenticationFailureHandler())
				.and().csrf().and().logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/logoutSuccess")
						.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES))));
//        .logoutSuccessHandler(logoutSuccessHandler())

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http, DataSource dataSource, PasswordEncoder passwordEncoder,
			CustomJpaUserDetailsService userDetailsService) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider);

		return authenticationManagerBuilder.build();
	}
}
