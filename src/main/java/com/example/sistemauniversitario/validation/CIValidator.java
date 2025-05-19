package com.example.sistemauniversitario.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CIValidator implements ConstraintValidator<ValidCI, String> {

    @Override
    public boolean isValid(String ci, ConstraintValidatorContext context) {
        return ci != null && ci.matches("^\\d{7,9}$");
    }
}
