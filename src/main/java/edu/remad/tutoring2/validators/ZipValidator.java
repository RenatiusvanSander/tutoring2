package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.dto.SignupDto;
import edu.remad.tutoring2.validators.annotations.Zip;

public class ZipValidator implements ConstraintValidator<Zip, SignupDto> {

	@Override
	public boolean isValid(SignupDto value, ConstraintValidatorContext context) {
		if (value == null || value.getZipCode().isBlank() || value.getZipCode().length() == 5
				|| !Pattern.matches(RegexAppConstants.ZIP_REGEX, value.getZipCode())) {
			return false;
		}

		return true;
	}

}
