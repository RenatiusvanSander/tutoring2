package edu.remad.tutoring2.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.remad.tutoring2.jwt.Tutoring2CustomJwtAuthenticationConverter;
import edu.remad.tutoring2.security.ContentSecurityPolicySettings;
import edu.remad.tutoring2.security.filters.DebugLoggingFilter;
import edu.remad.tutoring2.security.filters.HttpHeadersFilter;
import edu.remad.tutoring2.security.filters.TenantFilter;

@Configuration
public class SecurityFilterChainsConfig {

	private static final ClearSiteDataHeaderWriter.Directive[] COOKIES = Directive.values();

	@Autowired
	private ContentSecurityPolicySettings contentSecurityPolicies;

	@Autowired
	private Tutoring2CustomJwtAuthenticationConverter jwtAuthConverter;
	
	/**
	 * 
	 * @param http similar to spring security xml config for filtering request
	 * @return created security filter chain, {@link SecurityFilterChain}
	 * @throws Exception
	 */
	@Bean
	@Order(1)
	SecurityFilterChain oauth2rescourceserverSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher(AntPathRequestMatcher.antMatcher("/v2/**"))
				.authorizeHttpRequests(requests -> requests.anyRequest().authenticated()).csrf(csrf -> csrf.disable())
				.oauth2ResourceServer(server -> server.jwt().jwtAuthenticationConverter(jwtAuthConverter))
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
	}

	/**
	 * Does form login filter chain and has also http security.
	 * 
	 * @param http similar to spring security xml config for filtering request
	 * @return created security filter chain, {@link SecurityFilterChain}
	 * @throws Exception
	 */
	@Bean
	@Order(2)
	SecurityFilterChain formloginSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().headers(headers -> headers.xssProtection().and()
                .contentSecurityPolicy(contentSecurityPolicies.getContentSecurityPolicies()));

        http.addFilterAfter(new TenantFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new HttpHeadersFilter(), HeaderWriterFilter.class)
                .addFilterAfter(new DebugLoggingFilter(), HttpHeadersFilter.class)
                .securityContext((securityContext) -> securityContext.requireExplicitSave(true))
                .sessionManagement(
                        session -> session.maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login"))
                .authorizeRequests(requests -> requests.antMatchers("/", "/helloWorld", "/logoutSuccess", "/signup", "/api/v1/csrf")
                        .permitAll().antMatchers("/hello", "/bye", "/login", "/logout", "/templates/**").authenticated())
                .formLogin(login -> login.loginPage("/myCustomLogin").loginProcessingUrl("/process-login")
                        .defaultSuccessUrl("/hello", true)).csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/logoutSuccess")
                        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES))));
//        .logoutSuccessHandler(logoutSuccessHandler())

		return http.build();
	}

}
