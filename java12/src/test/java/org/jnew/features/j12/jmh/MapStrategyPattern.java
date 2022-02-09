package org.jnew.features.j12.jmh;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.time.DayOfWeek.*;

class MapStrategyPattern implements NumberCruncher {

    public MapStrategyPattern() {
    }

    /**
     * Strategy is defined in a static fashion
     */
    private static final Map<DayOfWeek, Function<Function<Number, Number>, Number>> rules = new HashMap<>() {{
        put(MONDAY, doSomeWork -> doSomeWork.apply(20L));
        put(TUESDAY, doSomeWork -> doSomeWork.apply(30L));
        put(WEDNESDAY, doSomeWork -> doSomeWork.apply(40L));
        put(THURSDAY, doSomeWork -> doSomeWork.apply(35L));
        put(FRIDAY, doSomeWork -> doSomeWork.apply(35L));
        put(SATURDAY, doSomeWork -> doSomeWork.apply("RELAX".hashCode()));
        put(SUNDAY, doSomeWork -> doSomeWork.apply("RELAX".hashCode()));

        put(null, doSomeWork -> doSomeWork.apply("??? WAIT WHAT ???".hashCode()));
    }};

    public Number crunch(DayOfWeek source, Function<Number, Number> doSomeWork) {
        return rules.getOrDefault(source, rules.get(null)).apply(doSomeWork);
    }

}
