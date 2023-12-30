package edu.remad.tutoring2.validators;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.remad.tutoring2.validators.annotations.CreationDate;

public class CreationDateValidator implements ConstraintValidator<CreationDate, LocalDateTime> {

	@Override
	public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
		return true;
	}
}
