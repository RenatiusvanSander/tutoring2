package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.validators.annotations.ValidUsername;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isBlank() || value.length() <= 4 || value.length() > 50
				|| !Pattern.matches(RegexAppConstants.USERNAME_REGEX, value)) {
			return false;
		}

		return true;
	}
}
