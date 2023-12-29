package edu.remad.tutoring2.tutoringconfig;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import edu.remad.tutoring2.appconstants.CORSAppConstants;
import edu.remad.tutoring2.appconstants.JavaAppConstants;
import edu.remad.tutoring2.appconstants.PackagesAppConstants;
import edu.remad.tutoring2.appconstants.SmtpAppConstants;
import edu.remad.tutoring2.appconstants.ViewResolversAppConstants;
import edu.remad.tutoring2.security.interceptors.GlobalInterceptor;
import edu.remad.tutoring2.security.interceptors.HandlerTimeLoggingInterceptor;
import edu.remad.tutoring2.security.interceptors.SecuritySignupFilter;
import edu.remad.tutoring2.systemenvironment.SystemEnvironment;
import edu.remad.tutoring2.systemenvironment.SystemEnvironmentFactory;

@EnableWebMvc
@Configuration
@ComponentScan(PackagesAppConstants.EDU_REMAD_TUTORING2)
public class TutoringWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(ViewResolversAppConstants.PATH_WEB_INF + "view/");
		viewResolver.setSuffix(ViewResolversAppConstants.JSP_SUFFIX);
		viewResolver.setOrder(1);

		return viewResolver;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/static-resources/**").addResourceLocations("/static-resources")
				.setCachePeriod(365 * 24 * 60 * 60).resourceChain(true).addResolver(new EncodedResourceResolver())
				.addResolver(new PathResourceResolver());
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars");
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		// TODO registry.addInterceptor(new LoggerInterceptor());
		// TODO registry.addInterceptor(new UserInterceptor());
		// TODO registry.addInterceptor(new SessionTimerInterceptor());
		registry.addInterceptor(new HandlerTimeLoggingInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/secure/**");
		registry.addInterceptor(new SecuritySignupFilter()).addPathPatterns("/process-signup");
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/project/**");
	}

	@Bean
	public SystemEnvironment systemEnvironment() {
		return SystemEnvironmentFactory.getInstance();
	}

	@Bean
	public JavaMailSender createJavaMailSender(SystemEnvironment systemEnvironment) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.web.de");
		mailSender.setPort(587);
		mailSender.setUsername(systemEnvironment.getSmtpUsername());
		mailSender.setPassword(systemEnvironment.getSmtpPassword());

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", JavaAppConstants.TRUE);
		props.put("mail.smtp.starttls.enable", JavaAppConstants.TRUE);
		props.put("mail.properties.mail.smtp.starttls.required", JavaAppConstants.TRUE);
		props.put("mail.properties.mail.smtp.ssl.enable", JavaAppConstants.FALSE);
		props.put("mail.properties.mail.smtp.timeout", SmtpAppConstants.SMTP_TIMEOUT_15_SECONDS_IN_MS);
		props.put("mail.properties.mail.smtp.connectiontimeout", SmtpAppConstants.SMTP_TIMEOUT_15_SECONDS_IN_MS);
		props.put("mail.properties.mail.smtp.socketFactory.fallback", JavaAppConstants.TRUE);
		props.put("mail.debug", JavaAppConstants.TRUE);

		return mailSender;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.freeMarker().cache(true).cacheLimit(1000);
		registry.jsp(ViewResolversAppConstants.PATH_WEB_INF + "view/", ViewResolversAppConstants.JSP_SUFFIX);
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("messages/messages", "messages/welcomes");
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();

		return localeChangeInterceptor;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/cors/greeting-javaconfig").allowedOrigins(CORSAppConstants.CORS_LOCALHOST_PORT_8080);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true).parameterName("format")
				.mediaType("xml", MediaType.APPLICATION_XML).mediaType("json", MediaType.APPLICATION_JSON);
	}
}
