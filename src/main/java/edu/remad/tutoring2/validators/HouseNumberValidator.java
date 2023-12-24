package edu.remad.tutoring2.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import edu.remad.tutoring2.dto.SignupDto;
import edu.remad.tutoring2.validators.annotations.HouseNumber;

public class HouseNumberValidator implements ConstraintValidator<HouseNumber, SignupDto>{

	@Override
	public boolean isValid(SignupDto value, ConstraintValidatorContext context) {
		if (value == null || !Pattern.matches(RegexAppConstants.HOUSE_NUMBER_REGEX, value.getAddressHouseNo())) {
			return false;
		}

		return true;
	}
}
