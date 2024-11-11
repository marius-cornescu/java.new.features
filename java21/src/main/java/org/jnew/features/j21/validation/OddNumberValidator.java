package org.jnew.features.j21.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OddNumberValidator  implements ConstraintValidator<OddNumber, Object> {
    private String message;

    @Override
    public void initialize(final OddNumber constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return ((Number) value).longValue() % 2 != 0;
    }
}

