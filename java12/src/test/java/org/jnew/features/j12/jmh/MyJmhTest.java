package org.jnew.features.j12.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.time.DayOfWeek.*;

@Fork(value = 3, warmups = 0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5, time = 10)
@Measurement(iterations = 5, time = 10)
/*
 * Thread: Each thread running the benchmark will create its own instance of the state object.
 * Group: Each thread group running the benchmark will create its own instance of the state object.
 * Benchmark: All threads running the benchmark share the same state object.
 */
@State(Scope.Benchmark)
public class MyJmhTest {

    private final DayOfWeek[] dataSource = new DayOfWeek[]{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};

    private static final int TARGET_INDEXES_COUNT = 1000;
    private int[] targetIndexes;

    @Setup
    public void prepareTest() {
        targetIndexes = new int[TARGET_INDEXES_COUNT];

        for (int i = 0; i < TARGET_INDEXES_COUNT; i++) {
            targetIndexes[i] = (int) Math.floor(Math.random() * dataSource.length);
        }
    }

    @Benchmark
    public Number[] switchExpression() {
        Number[] results = new Number[TARGET_INDEXES_COUNT];

        for (int i = 0, targetIndexesLength = targetIndexes.length; i < targetIndexesLength; i++) {
            int index = targetIndexes[i];
            results[i] = useSwitchExpression(dataSource[index],
                    number -> {
                        Blackhole.consumeCPU(256);
                        return number;
                    }
            );
        }

        return results;
    }

    @Benchmark
    public Number[] mapStrategyPattern() {
        Number[] results = new Number[TARGET_INDEXES_COUNT];

        for (int i = 0, targetIndexesLength = targetIndexes.length; i < targetIndexesLength; i++) {
            int index = targetIndexes[i];
            results[i] = useMapStrategyPattern(dataSource[index],
                    number -> {
                        Blackhole.consumeCPU(256);
                        return number;
                    }
            );
        }

        return results;
    }

    private Number useSwitchExpression(DayOfWeek source, Function<Number, Number> doSomeWork) {
        return switch (source) {
            case MONDAY -> doSomeWork.apply(20L);
            case TUESDAY -> doSomeWork.apply(30L);
            case WEDNESDAY -> doSomeWork.apply(40L);
            case THURSDAY, FRIDAY -> doSomeWork.apply(35L);

            case SATURDAY, SUNDAY -> doSomeWork.apply("RELAX".hashCode());

            default -> doSomeWork.apply("??? WAIT WHAT ???".hashCode());
        };
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

    private Number useMapStrategyPattern(DayOfWeek source, Function<Number, Number> doSomeWork) {
        return rules.getOrDefault(source, rules.get(null)).apply(doSomeWork);
    }
}
