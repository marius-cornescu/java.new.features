package org.jnew.features.j21;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public class ValidatedFinancingObject {
    @NotEmpty
    private List<@NotNull @Valid Proposal> proposals;
}
