package org.jnew.features.j25;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

@Slf4j
class SampleTest {

    static Stream<Arguments> variousValues() {
        return Stream.of(
                of("1 second SleepyRunnable IN SinglePlatformThread",
                        makeMeAOneSecondSeepyRunnable(), (Consumer<Runnable>) SampleTest::runMeInAPlatformThread),

                of("1 second SleepyRunnable IN SingleVirtualThread",
                        makeMeAOneSecondSeepyRunnable(), (Consumer<Runnable>) SampleTest::runMeInAVirtualThread)

        );
    }

    private static Runnable makeMeAOneSecondSeepyRunnable() {
        return () -> {
            try {
                Thread.sleep(1000);
                log.atInfo().addArgument(Thread.currentThread()).log("DONE by [{}}]");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static void runMeInAPlatformThread(Runnable task) {
        try {
            Thread vt = Thread.ofPlatform().start(task);
            vt.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runMeInAVirtualThread(Runnable task) {
        try {
            Thread vt = Thread.ofVirtual().start(task);
            vt.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "[{index}]: {0}")
    @MethodSource("variousValues")
    void lastElementOfCollectionsIs(String scenarioName, Runnable taskToRun, Consumer<Runnable> taskExecutor) {
        // given
        long startTime = System.currentTimeMillis();
        log.atInfo().log("START");

        // when
        taskExecutor.accept(taskToRun);

        // then
        log.atInfo().addArgument(System.currentTimeMillis() - startTime).log("Execution took [{}] mills");
    }

}
