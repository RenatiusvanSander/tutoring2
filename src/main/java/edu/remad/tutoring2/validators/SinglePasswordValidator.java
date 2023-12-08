package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.validators.annotations.SinglePassword;

public class SinglePasswordValidator implements ConstraintValidator<SinglePassword, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isBlank() || value.length() <= 7 || value.length() > 500
				|| !Pattern.matches(RegexAppConstants.PASSWORD_REGEX, value)) {
			return false;
		}

		return true;
	}
}
