package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.dto.SignupDto;
import edu.remad.tutoring2.validators.annotations.EqualRepeatedPasswords;

public class EqualRepeatedPasswordValidator implements ConstraintValidator<EqualRepeatedPasswords, SignupDto> {

	@Override
	public boolean isValid(SignupDto value, ConstraintValidatorContext context) {
		if (value == null || value.getPassword().isBlank() || value.getRepeatedPassword().isBlank()
				|| value.getPassword().length() <= 7 || value.getRepeatedPassword().length() <= 7
				|| value.getPassword().length() > 500 || value.getRepeatedPassword().length() > 500
				|| !Pattern.matches(RegexAppConstants.PASSWORD_REGEX, value.getPassword())
				|| !Pattern.matches(RegexAppConstants.PASSWORD_REGEX, value.getRepeatedPassword())) {
			return false;
		}

		return value.getPassword().equals(value.getRepeatedPassword());
	}
}
