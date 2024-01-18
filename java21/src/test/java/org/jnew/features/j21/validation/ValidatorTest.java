package org.jnew.features.j21.validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {



    class ValidatedFinancingObject {
        @NotEmpty
        private List<@NotNull @Valid Proposal> proposals;
    }

    class Proposal {
        @NotEmpty
        private List<@NotNull String> terms;
        @NotEmpty
        private List<@NotNull @Valid @OddNumber Number> products;
    }

}