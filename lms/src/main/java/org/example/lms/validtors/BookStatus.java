package org.example.lms.validtors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^(AVAILABLE|BORROWED)$", message = "Invalid book status")
public @interface BookStatus {
    String message() default "Invalid book status";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
