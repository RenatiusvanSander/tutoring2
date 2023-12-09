package edu.remad.tutoring2.services;

public interface VerificationService {

	boolean saveVerificationNumber(String email, String verficationNumber);
	
	boolean isVerified(String verificationNumber);
	
	void deleteVerification(String email);

	String getEmail(String verificationNumber);
}
