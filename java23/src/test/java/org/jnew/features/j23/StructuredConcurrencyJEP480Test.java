package org.jnew.features.j23;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

@Slf4j
class StructuredConcurrencyJEP480Test {

    static Stream<Arguments> variousValues() {
        return Stream.of(
                of("1 second SleepyRunnable IN SinglePlatformThread",
                        makeMeAOneSecondSeepyRunnable(), (Consumer<Runnable>) StructuredConcurrencyJEP480Test::runMeInAPlatformThread),

                of("1 second SleepyRunnable IN SingleVirtualThread",
                        makeMeAOneSecondSeepyRunnable(), (Consumer<Runnable>) StructuredConcurrencyJEP480Test::runMeInAVirtualThread),

                of("1 second SleepyRunnable IN 10k PlatformThreads",
                        makeMeAOneSecondSeepyRunnable(), (Consumer<Runnable>)task -> runMeInManyPlatformThreads(task, 10000)),

                of("1 second SleepyRunnable IN 10k VirtualThreads",
                        makeMeAOneSecondSeepyRunnable(), (Consumer<Runnable>)task -> runMeInManyVirtualThreads(task, 10000)),

                of("1 second SleepyRunnable IN 10k ExecutorVirtualThreads",
                        makeMeAOneSecondSeepyRunnable(), (Consumer<Runnable>)task -> runMeInManyVirtualThreadsWithExecutor(task, 10000))
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

    // will throw NPE
    private static Runnable makeMeAHttpGettingRunnable() {
        return () -> {
            HttpClient httpClient = null;
            try {
                HttpRequest getRequest = HttpRequest.newBuilder().GET().build();
                HttpResponse<String> response =
                        httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    log.atInfo().log("Success!!!");
                }
            } catch (IOException | InterruptedException e) {
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

    private static void runMeInManyPlatformThreads(Runnable task, int threadsCount) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            threads.add(Thread.ofPlatform().start(task));
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runMeInManyVirtualThreads(Runnable task, int threadsCount) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            threads.add(Thread.ofVirtual().start(task));
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runMeInManyVirtualThreadsWithExecutor(Runnable task, int threadsCount) {
        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < threadsCount; i++) {
                es.submit(task);
            }
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
