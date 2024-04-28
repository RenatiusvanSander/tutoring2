package edu.remad.tutoring2.services.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.OperationNotSupportedException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import edu.remad.tutoring2.services.EmailService;
import edu.remad.tutoring2.systemenvironment.SystemEnvironment;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
@Primary
@Qualifier("emailServiceImpl")
public class EmailServiceImpl implements EmailService {
	
	public static final String VERIFICATION_LINK_SUBJECT = "Activate your pupil account";
	
	public static final String VERIFICATION_LINK_KEY = "verificationLink";
	
	public static final String EMAIL_TITLE_KEY= "emailTitle";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SystemEnvironment env;
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfig;

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setFrom(env.getSmtpUsername());
		emailMessage.setTo(to);
		emailMessage.setSubject(subject);
		emailMessage.setText(text);
		emailMessage.setSentDate(Date.valueOf( LocalDateTime.now().toLocalDate()));
		
		mailSender.send(emailMessage);
	}

	@Override
	public void sendSimpleMessageUsingTemplate(String to, String subject, String... templateModel) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("This method is not supported: sendMessageWithAttachment.");
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("This method is not supported: sendMessageWithAttachment.");
	}

	@Override
	public void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel)
			throws IOException, MessagingException, OperationNotSupportedException {
		throw new OperationNotSupportedException("This method is not supported: sendMessageUsingThymeleafTemplate.");
	}

	@Override
	public void sendMessageUsingFreemarkerTemplate(String to, String subject, String templateName ,Map<String, Object> templateModel)
			throws IOException, TemplateException, MessagingException, OperationNotSupportedException {
		Template template = freeMarkerConfig.getConfiguration().getTemplate(templateName);
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
		
		String from = env.getSmtpUsername();
		sendHtmlMailWithAttachmnent(from, to, subject, html, null, null);
	}
	
	private void sendHtmlMailWithAttachmnent(String from, String to, String subject, String htmlBody, String fileNameAndFileExtension, ByteArrayResource fileContent) throws MessagingException {
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
	    helper.setFrom(from);
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(htmlBody, true);
	    
	    if(StringUtils.isNotBlank(fileNameAndFileExtension) && fileContent.contentLength() > 0) {
	    	helper.addAttachment(fileNameAndFileExtension, fileContent);
	    }
	    
	    mailSender.send(message);
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String templateName,
			Map<String, Object> templateModel, String fileName, byte[] attachment)
			throws OperationNotSupportedException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, MessagingException {
		Template template = freeMarkerConfig.getConfiguration().getTemplate(templateName);
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
		
		String from = env.getSmtpUsername();
		ByteArrayResource fileContent = new ByteArrayResource(attachment);
		sendHtmlMailWithAttachmnent(from, to, subject, html, fileName, fileContent);
	}
}
