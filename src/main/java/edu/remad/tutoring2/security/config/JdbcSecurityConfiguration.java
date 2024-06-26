package edu.remad.tutoring2.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;

import com.mysql.cj.jdbc.Driver;

import edu.remad.tutoring2.security.ContentSecurityPolicySettings;
import edu.remad.tutoring2.security.filters.DebugLoggingFilter;
import edu.remad.tutoring2.security.filters.HttpHeadersFilter;
import edu.remad.tutoring2.security.filters.TenantFilter;
import edu.remad.tutoring2.services.impl.CustomJpaUserDetailsService;

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
				.addFilterAfter(new HttpHeadersFilter(), HeaderWriterFilter.class)
				.addFilterAfter(new DebugLoggingFilter(), HttpHeadersFilter.class)
				.securityContext((securityContext) -> securityContext.requireExplicitSave(true))
				.sessionManagement(
						session -> session.maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login"))
				.authorizeRequests().antMatchers("/", "/helloWorld", "/logoutSuccess", "/signup", "/api/v1/csrf")
				.permitAll().antMatchers("/hello", "/bye", "/login", "/logout", "/templates/**").authenticated().and()
				.formLogin().loginPage("/myCustomLogin").loginProcessingUrl("/process-login")
				.defaultSuccessUrl("/hello", true)
//        .failureUrl("/login.html?error=true")
//        .failureHandler(authenticationFailureHandler())
				.and().csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/logoutSuccess")
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
