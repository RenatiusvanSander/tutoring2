package edu.remad.tutoring2.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import edu.remad.tutoring2.validators.HouseNumberValidator;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { HouseNumberValidator.class })
public @interface HouseNumber {

	String message() default "{edu.remad.springconfig.validators.annotations.HouseNumber}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
