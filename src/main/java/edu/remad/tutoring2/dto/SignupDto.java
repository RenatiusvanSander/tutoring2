package edu.remad.tutoring2.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import edu.remad.tutoring2.validators.annotations.EqualRepeatedEmail;
import edu.remad.tutoring2.validators.annotations.HouseNumber;
import edu.remad.tutoring2.validators.annotations.Location;
import edu.remad.tutoring2.validators.annotations.SinglePassword;
import edu.remad.tutoring2.validators.annotations.ValidUsername;
import edu.remad.tutoring2.validators.annotations.Zip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupDto {

	@ValidUsername
	private String username;

	@SinglePassword
	private String password;

	@SinglePassword
	private String repeatedPassword;

	@EqualRepeatedEmail
	private String email;

	@EqualRepeatedEmail
	private String repeatedEmail;

	private String addressStreet;

	@HouseNumber
	private String addressHouseNo;
	
	@Zip
	private String zipCode;
	
	@Location
	private String zipCodeLocation;

	private LocalDateTime zipCodeCreationDate = LocalDateTime.now();
}
