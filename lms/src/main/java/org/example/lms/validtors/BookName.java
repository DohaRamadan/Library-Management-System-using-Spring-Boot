package org.example.lms.validtors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Constraint(validatedBy = {})
@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Book name must contain only alphabetic, numeric characters, or spaces")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookName {
    String message() default "Book name must contain only alphabetic, numeric characters, or spaces";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}