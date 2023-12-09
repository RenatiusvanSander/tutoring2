package edu.remad.tutoring2.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import edu.remad.tutoring2.validators.EqualRepeatedPasswordValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { EqualRepeatedPasswordValidator.class })
public @interface EqualRepeatedPasswords {

	String message() default "{edu.remad.tutoring2.validators.annotations.EuqalRepeatedPassword}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
