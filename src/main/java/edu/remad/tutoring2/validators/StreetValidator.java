package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.dto.SignupDto;
import edu.remad.tutoring2.validators.annotations.Street;

public class StreetValidator implements ConstraintValidator<Street, SignupDto> {

	@Override
	public boolean isValid(SignupDto value, ConstraintValidatorContext context) {
		if (value == null || !Pattern.matches(RegexAppConstants.STREET_REGEX, value.getAddressStreet())) {
			return false;
		}

		return true;
	}

}
