package edu.remad.tutoring2.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegistrationDto {
	
	@NotBlank
	private String username;
	
	@NotBlank
	@Min(3)
	@Max(256)
	@Email
	private String email;
	
	@NotBlank
	@Min(8)
	@Max(500)
	private String password;
	
	@NotBlank
	@Max(256)
	private String firstName;
	
	@NotBlank
	@Max(256)
	private String lastName;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	@Max(256)
	private String cellPhone;
	
	@NotBlank
	@Max(256)
	private String addressStreet;

	@NotBlank
	@Max(256)
	//@HouseNumber
	private String addressHouseNo;
	
	@NotBlank
	@Max(256)
//	@Zip
	private String zipCode;
	
	@NotBlank
	@Max(256)
//	@Location
	private String zipCodeLocation;

//	@CreationDate
	private LocalDateTime zipCodeCreationDate;
}
