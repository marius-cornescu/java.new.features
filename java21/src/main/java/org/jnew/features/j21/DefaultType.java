package org.jnew.features.j21;

import lombok.Getter;

public interface DefaultType {
    String getName();
}

@Getter
class AType implements DefaultType {

    private final long timeInSeconds;

    AType(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public String getName() {
        return "Type A";
    }

}

@Getter
class BType implements DefaultType {

    private final long timeInMinutes;

    BType(long timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    @Override
    public String getName() {
        return "Type A";
    }
}
