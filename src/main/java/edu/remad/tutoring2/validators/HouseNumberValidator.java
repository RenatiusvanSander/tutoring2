package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.validators.annotations.HouseNumber;

public class HouseNumberValidator implements ConstraintValidator<HouseNumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isBlank() || !Pattern.matches(RegexAppConstants.HOUSE_NUMBER_REGEX, value)) {
			return false;
		}

		return true;
	}
}
