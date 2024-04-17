package org.example.lms.validtors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Size(min=1, max=255)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^(.+)@(\\S+)$", message = "Invalid Email Format")
public @interface Email {
    String message() default "Invalid Patron Email";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
