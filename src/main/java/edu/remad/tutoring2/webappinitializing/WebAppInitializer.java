package edu.remad.tutoring2.webappinitializing;

import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import edu.remad.tutoring2.security.config.CorsSecurityConfig;
import edu.remad.tutoring2.security.config.JPASecurityConfig;
import edu.remad.tutoring2.security.config.JdbcSecurityConfiguration;
import edu.remad.tutoring2.security.config.SpringSecurityConfig;
import edu.remad.tutoring2.tutoringconfig.LocaleResolverConfig;
import edu.remad.tutoring2.tutoringconfig.TutoringWebMvcConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringSecurityConfig.class, JdbcSecurityConfiguration.class, JPASecurityConfig.class,
				LocaleResolverConfig.class, CorsSecurityConfig.class, TutoringWebMvcConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Bean
	@Override
	public WebApplicationContext createRootApplicationContext() {
		Class<?>[] configClasses = getRootConfigClasses();
		if (!ObjectUtils.isEmpty(configClasses)) {
			AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
			context.register(configClasses);
			return context;
		} else {
			return null;
		}
	}
}