package org.example.lms.validtors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Size(min=1, max=20)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^(\\+*[0-9]+)$", message = "Invalid Phone Number Format")
public @interface PhoneNumber {
    String message() default "Invalid Mobile Number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
