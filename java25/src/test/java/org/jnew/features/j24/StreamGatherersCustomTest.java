package org.jnew.features.j24;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

@Slf4j
class StreamGatherersCustomTest {

    Stream<String> letters = Stream.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
    Stream<Integer> numbers = Stream.of(5, 2, 10, 7, 3, 1);

    static class DiyIntegrator<STATE, INPUT, OUTPUT> implements Gatherer.Integrator<STATE, INPUT, OUTPUT> {

        @Override
        public boolean integrate(STATE state, INPUT element, Gatherer.Downstream<? super OUTPUT> downstream) {
            return false;
        }
    }

    static class DiyGatherer<INPUT, STATE, OUTPUT> implements Gatherer<INPUT, STATE, OUTPUT> {

        @Override
        public  Integrator<STATE, INPUT, OUTPUT> integrator() {
            return new DiyIntegrator<STATE, INPUT, OUTPUT>();
        }
    }

    @Test
    void customGatherers_fold() {

        var gatherer = Gatherer.of(
                (_, element, downstream) -> {
                    return downstream.push(element);
                }
        );

        var result = letters.gather(gatherer).toList();

        log.atInfo().addArgument(result).log("folded letters = [{}]");

        // THEN
        var sa = new SoftAssertions();
        sa.assertThat(result).isNotNull()
                .first()
                .satisfies(allLetters -> {
                    sa.assertThat(allLetters).isEqualTo("abcdefghijk");
                });

        sa.assertAll();
    }

}
