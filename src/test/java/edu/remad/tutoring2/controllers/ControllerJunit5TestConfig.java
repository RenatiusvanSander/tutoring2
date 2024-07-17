package edu.remad.tutoring2.controllers;

import org.springframework.beans.BeansException;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.remad.tutoring2.security.AuthenticationFacade;
import edu.remad.tutoring2.services.impl.AddressServiceImpl;
import edu.remad.tutoring2.services.impl.EmailServiceImpl;
import edu.remad.tutoring2.services.impl.ReminderServiceImpl;
import edu.remad.tutoring2.services.impl.ServiceContractServiceImpl;
import edu.remad.tutoring2.services.impl.TutoringAppointmentServiceImpl;
import edu.remad.tutoring2.services.impl.UserServiceImpl;
import edu.remad.tutoring2.services.impl.VerificationLinkCreationServiceImpl;
import edu.remad.tutoring2.services.impl.VerificationServiceImpl;

@MockBeans({ @MockBean(AddressServiceImpl.class), @MockBean(UserServiceImpl.class), @MockBean(EmailServiceImpl.class),
		@MockBean(VerificationLinkCreationServiceImpl.class), @MockBean(VerificationServiceImpl.class),
		@MockBean(ServiceContractServiceImpl.class), @MockBean(AuthenticationFacade.class),
		@MockBean(TutoringAppointmentServiceImpl.class), @MockBean(ReminderServiceImpl.class) })
@Configuration
@ComponentScan(basePackages = { "edu.remad.tutoring2.controllers" })
public class ControllerJunit5TestConfig implements WebMvcConfigurer, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
