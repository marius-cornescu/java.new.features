package org.jnew.features.j21;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.jnew.features.j21.validation.OddNumber;

import java.util.List;

public class Proposal {
    @NotEmpty
    private List<@NotNull String> terms;
    @NotEmpty
    private List<@NotNull @Valid @OddNumber Number> products;
}
