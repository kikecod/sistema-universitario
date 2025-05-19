package com.example.sistemauniversitario.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CIValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCI {
    String message() default "El CI debe tener entre 7 y 9 dígitos numéricos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}