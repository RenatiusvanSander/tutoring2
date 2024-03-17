package edu.remad.tutoring2.tutoringconfig;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import edu.remad.tutoring2.appconstants.JavaAppConstants;
import edu.remad.tutoring2.appconstants.SmtpAppConstants;
import edu.remad.tutoring2.systemenvironment.SystemEnvironment;

@Configuration
public class SpringJavaMailConfig {

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
}
