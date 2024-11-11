package org.jnew.features.j21.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.jnew.features.j21.ValidatedFinancingObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;
import java.util.Set;

class ValidatorTest {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ValidatorTest.class);

    @Test
    void validateBean() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            var validatedFinancingObject = newValidatedFinancingObject();

            Set<ConstraintViolation<ValidatedFinancingObject>> violations = validator.validate(validatedFinancingObject);

            for (ConstraintViolation<ValidatedFinancingObject> violation : violations) {
                log.warn(violation.getMessage());
            }
        }
    }

    private ValidatedFinancingObject newValidatedFinancingObject() {
        return ValidatedFinancingObject.builder()
                .build();
    }

}