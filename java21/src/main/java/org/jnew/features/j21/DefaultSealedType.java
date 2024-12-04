package org.jnew.features.j21;

import lombok.Getter;

public sealed interface DefaultSealedType {
    String getName();
}

@Getter
final class ASealedType implements DefaultSealedType {

    private final long timeInSeconds;

    ASealedType(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public String getName() {
        return "Type A";
    }

}

@Getter
final class BSealedType implements DefaultSealedType {

    private final long timeInMinutes;

    BSealedType(long timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    @Override
    public String getName() {
        return "Type A";
    }
}
