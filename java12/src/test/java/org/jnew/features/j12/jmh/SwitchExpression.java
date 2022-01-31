package org.jnew.features.j12.jmh;

import java.time.DayOfWeek;
import java.util.function.Function;

class SwitchExpression implements NumberCruncher {

    public SwitchExpression() {
    }

    public Number crunch(DayOfWeek source, Function<Number, Number> doSomeWork) {
        return switch (source) {
            case MONDAY -> doSomeWork.apply(20L);
            case TUESDAY -> doSomeWork.apply(30L);
            case WEDNESDAY -> doSomeWork.apply(40L);
            case THURSDAY, FRIDAY -> doSomeWork.apply(35L);

            case SATURDAY, SUNDAY -> doSomeWork.apply("RELAX".hashCode());

            default -> doSomeWork.apply("??? WAIT WHAT ???".hashCode());
        };
    }


}
