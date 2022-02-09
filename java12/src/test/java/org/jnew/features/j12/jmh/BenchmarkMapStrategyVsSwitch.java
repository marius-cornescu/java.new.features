package org.jnew.features.j12.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.reflect.InvocationTargetException;
import java.time.DayOfWeek;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.time.DayOfWeek.*;

@Fork(value = 3, warmups = 0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 20, time = 10)
@Measurement(iterations = 10, time = 10)
/*
 * Thread: Each thread running the benchmark will create its own instance of the state object.
 * Group: Each thread group running the benchmark will create its own instance of the state object.
 * Benchmark: All threads running the benchmark share the same state object.
 */
@State(Scope.Benchmark)
public class BenchmarkMapStrategyVsSwitch {

    private static final Random rnd = new Random();
    private final DayOfWeek[] dataSource = new DayOfWeek[]{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};

    private static final int TARGET_INDEXES_COUNT = 1000;
    private int[] targetIndexes;

    @Param({"0", "1"}) int numberCruncherIndex;
    private NumberCruncher numberCruncher;
    private static final Class<? super NumberCruncher>[] NUMBER_CRUNCHERS = new Class[]{MapStrategyPattern.class, SwitchExpression.class};

    @Setup
    public void prepareTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        numberCruncher = (NumberCruncher) NUMBER_CRUNCHERS[numberCruncherIndex].getConstructor().newInstance();

        targetIndexes = new int[TARGET_INDEXES_COUNT];

        for (int i = 0; i < TARGET_INDEXES_COUNT; i++) {
            targetIndexes[i] = (int) Math.floor(rnd.nextInt(dataSource.length));
        }
    }

    @Benchmark
    public Number[] crunchThemNumbers(Blackhole bh) {
        Number[] results = new Number[TARGET_INDEXES_COUNT];

        for (int i = 0, targetIndexesLength = targetIndexes.length; i < targetIndexesLength; i++) {
            int index = targetIndexes[i];
            results[i] = numberCruncher.crunch(dataSource[index],
                    number -> {
                        bh.consume(number);

                        return number;
                    }
            );
        }

        return results;
    }

}
