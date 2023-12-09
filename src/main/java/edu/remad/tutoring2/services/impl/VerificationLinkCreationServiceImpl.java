package edu.remad.tutoring2.services.impl;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.controllers.LoginController;
import edu.remad.tutoring2.globalexceptions.Error;
import edu.remad.tutoring2.globalexceptions.ErrorInfo;
import edu.remad.tutoring2.globalexceptions.HttpStatus500Exception;
import edu.remad.tutoring2.services.VerificationLinkCreationService;
import edu.remad.tutoring2.services.VerificationService;

@Service
public class VerificationLinkCreationServiceImpl implements VerificationLinkCreationService {

	private static final String LOCALIZED_MESSAGE = "We have a database error.";
	private static final String ADDITIONAL_MESSAGE = "We had an error in creating activation number. Please try again or contact our support.";
	private static final String SERVER_URI = "http://localhost:8080/tutoring2";
	private static final String QUERY_PARAM = "?verificationNumber=";

	@Autowired
	private BCryptPasswordEncoder verificationLinkEncoder;

	@Autowired
	private VerificationService verificationService;

	@Override
	public long createVerificationNumber() {
		return Clock.systemUTC().instant().toEpochMilli();
	}

	@Override
	public String encodeVerificationNumber(long verificationNumber) {
		String stringEncodedVerficationNumber = String.valueOf(verificationNumber);
		String encodedVerificatioNumber = verificationLinkEncoder.encode(stringEncodedVerficationNumber);

		return encodedVerificatioNumber;
	}

	@Override
	public boolean storeVerificationLinkNumber(String email, String verificationNumber) {
		boolean isToStore = !verificationService.isVerified(verificationNumber);

		return isToStore;
	}

	public String createVerificationLink(String encodedVerificationNumber) {
		String verificationLink = String.format("%s%s%s%s", SERVER_URI, LoginController.ACTIVATE_SIGNUP, QUERY_PARAM,
				encodedVerificationNumber);

		return verificationLink;
	}

	@Override
	public Map<String, Object> createVerficationLinkHtml(String email) {
		long verificationNumber = createVerificationNumber();
		String encodedVerificationNumber = encodeVerificationNumber(verificationNumber);

		if (!storeVerificationLinkNumber(encodedVerificationNumber, email)) {
			throw new HttpStatus500Exception(encodedVerificationNumber, new Throwable(), createErrorInfo());
		}

		String verificationLink = createVerificationLink(encodedVerificationNumber);

		Map<String, Object> templateModel = new HashMap<>();
		templateModel.put(EmailServiceImpl.EMAIL_TITLE_KEY, EmailServiceImpl.VERIFICATION_LINK_SUBJECT);
		templateModel.put(EmailServiceImpl.VERIFICATION_LINK_KEY, verificationLink);
		templateModel.put(VerificationServiceImpl.VERIFICATION_NUMBER_KEY, encodedVerificationNumber);

		return templateModel;
	}

	@Override
	public ErrorInfo createErrorInfo() {
		return new ErrorInfo(LoginController.SIGNUP, Error.HTTP_500_ERROR, ADDITIONAL_MESSAGE, LOCALIZED_MESSAGE);
	}
}
