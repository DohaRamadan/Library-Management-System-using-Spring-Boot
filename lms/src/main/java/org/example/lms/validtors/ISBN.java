package org.example.lms.validtors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Target;


@Constraint(validatedBy = {})
@Pattern(regexp = "^[0-9]+$", message = "ISBN must contain only numeric characters")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBN {
    String message() default "ISBN must contain only numeric characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
