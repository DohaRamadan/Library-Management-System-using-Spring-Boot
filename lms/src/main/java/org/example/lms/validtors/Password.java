package org.example.lms.validtors;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;

@Documented
@Size(min = 1, max = 255)
@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must be at least 8 characters long. Password must contain at least one lowercase letter, one uppercase letter, and one digit. Password must not contain any special characters.")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Password {
    String message() default "Password must be at least 8 characters long. Password must contain at least one lowercase letter, one uppercase letter, and one digit. Password must not contain any special characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
