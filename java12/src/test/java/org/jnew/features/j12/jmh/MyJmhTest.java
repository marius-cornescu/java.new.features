package org.jnew.features.j12.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

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

    private double rightX = Math.PI;
    private final double wrongX = Math.PI;

    @Benchmark
    public double wrong() {
        return Math.log(wrongX);
    }

    @Benchmark
    public double right() {
        return Math.log(rightX);
    }
}
