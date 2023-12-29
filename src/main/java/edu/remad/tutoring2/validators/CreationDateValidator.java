package edu.remad.tutoring2.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.dto.SignupDto;
import edu.remad.tutoring2.validators.annotations.CreationDate;

public class CreationDateValidator implements ConstraintValidator<CreationDate, SignupDto> {

	@Override
	public boolean isValid(SignupDto value, ConstraintValidatorContext context) {
		return true;
	}
}
