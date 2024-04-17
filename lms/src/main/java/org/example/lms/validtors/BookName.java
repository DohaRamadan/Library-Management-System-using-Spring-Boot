package org.example.lms.validtors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Constraint(validatedBy = {})
@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Book name must contain only alphabetic or numeric characters")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookName {
    String message() default "Book name must contain only alphabetic or numeric characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
