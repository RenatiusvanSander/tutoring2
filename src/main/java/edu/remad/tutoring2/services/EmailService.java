package edu.remad.tutoring2.services;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.naming.OperationNotSupportedException;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);

	void sendSimpleMessageUsingTemplate(String to, String subject, String... templateModel) throws OperationNotSupportedException;

	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws OperationNotSupportedException;
	
	void sendMessageWithAttachment(String to, String subject, String templateName, Map<String, Object> templateModel, String fileName, byte[] attachment) throws OperationNotSupportedException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, MessagingException;

	void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel)
			throws IOException, MessagingException, OperationNotSupportedException;

	void sendMessageUsingFreemarkerTemplate(String to, String subject, String templateName, Map<String, Object> templateModel)
			throws IOException, TemplateException, MessagingException, OperationNotSupportedException;
}