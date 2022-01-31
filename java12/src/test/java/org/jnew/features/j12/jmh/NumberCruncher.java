package org.jnew.features.j12.jmh;

import java.time.DayOfWeek;
import java.util.function.Function;

public interface NumberCruncher {

    Number crunch(DayOfWeek source, Function<Number, Number> doSomeWork);
}
