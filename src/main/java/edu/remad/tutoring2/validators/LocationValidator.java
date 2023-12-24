package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.dto.SignupDto;
import edu.remad.tutoring2.validators.annotations.Location;

public class LocationValidator implements ConstraintValidator<Location, SignupDto> {

	@Override
	public boolean isValid(SignupDto value, ConstraintValidatorContext context) {
		if (value == null || value.getZipCodeLocation().isBlank()
				|| !Pattern.matches(RegexAppConstants.ZIP_LOCATION_REGEX, value.getZipCode())) {
			return false;
		}

		return true;
	}

}
