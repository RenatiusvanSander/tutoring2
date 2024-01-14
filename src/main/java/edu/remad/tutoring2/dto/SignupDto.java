package edu.remad.tutoring2.dto;

import java.time.LocalDateTime;

import edu.remad.tutoring2.validators.annotations.CreationDate;
import edu.remad.tutoring2.validators.annotations.HouseNumber;
import edu.remad.tutoring2.validators.annotations.Location;
import edu.remad.tutoring2.validators.annotations.SinglePassword;
import edu.remad.tutoring2.validators.annotations.Street;
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

	private String firstName;

	private String lastName;

	private String gender;

	private String cellPhone;

	@ValidUsername
	private String username;

	@SinglePassword
	private String password;

	@SinglePassword
	private String repeatedPassword;

	private String email;

	private String repeatedEmail;

	// @Street
	private String addressStreet;

	// @HouseNumber
	private String addressHouseNo;

//	@Zip
	private String zipCode;

//	@Location
	private String zipCodeLocation;

//	@CreationDate
	private LocalDateTime zipCodeCreationDate = LocalDateTime.now();
}
