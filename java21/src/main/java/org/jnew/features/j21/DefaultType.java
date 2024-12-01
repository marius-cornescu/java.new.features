package org.jnew.features.j21;

import lombok.Getter;

public interface DefaultType {
    String getName();
}

class AType implements DefaultType {

    @Getter
    private final long timeInSeconds;

    AType(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public String getName() {
        return "Type A";
    }

}

class BType implements DefaultType {

    @Getter
    private final long timeInMinutes;

    BType(long timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    @Override
    public String getName() {
        return "Type A";
    }
}
