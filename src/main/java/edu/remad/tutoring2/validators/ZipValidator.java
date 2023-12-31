package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.validators.annotations.Zip;

public class ZipValidator implements ConstraintValidator<Zip, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isBlank() || value.length() == 5
				|| !Pattern.matches(RegexAppConstants.ZIP_REGEX, value)) {
			return false;
		}

		return true;
	}

}
