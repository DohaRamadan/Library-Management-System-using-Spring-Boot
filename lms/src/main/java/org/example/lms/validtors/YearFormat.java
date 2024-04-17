package org.example.lms.validtors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Constraint(validatedBy = {})
@Pattern(regexp = "^\\d{4}$", message = "Publication year must be in the format of years (e.g., YYYY)")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface YearFormat {
    String message() default "Publication year must be in the format of years (e.g., YYYY)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}