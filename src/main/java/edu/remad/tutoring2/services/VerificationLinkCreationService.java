package edu.remad.tutoring2.services;

import java.util.Map;

import edu.remad.tutoring2.globalexceptions.ErrorInfo;

public interface VerificationLinkCreationService {

	long createVerificationNumber();

	String encodeVerificationNumber(long verficationNumber);

	boolean storeVerificationLinkNumber(String email, String verificationLink);

	Map<String, Object> createVerficationLinkHtml(String email);

	ErrorInfo createErrorInfo();
}
