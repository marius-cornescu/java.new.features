package org.jnew.features.j24;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

@Slf4j
class StreamGatherersBuiltInTest {

    Stream<String> letters = Stream.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
    Stream<Integer> numbers = Stream.of(5, 2, 10, 7, 3, 1);

    @Test
    void builtInGatherers_fold() {

        List<String> result = letters.gather(Gatherers.fold(
                () -> "", (acc, elem) -> acc + elem
        )).toList();

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

    @Test
    void builtInGatherers_scan() {

        var result = numbers.gather(Gatherers.scan(
                () -> 0, (acc, elem) -> acc + elem
        )).toList();

        log.atInfo().addArgument(result).log("scan numbers = [{}]");

        // THEN
        var sa = new SoftAssertions();
        sa.assertThat(result).isNotNull()
                .last()
                .satisfies(lastVal -> {
                    sa.assertThat(lastVal).isEqualTo(28);
                });

        sa.assertAll();
    }

    @Test
    void builtInGatherers_windowFixed() {

        var result = letters.gather(Gatherers.windowFixed(5))
                .toList();

        log.atInfo().addArgument(result).log("folded letters = [{}]");

        // THEN
        var sa = new SoftAssertions();
        sa.assertThat(result).isNotNull()
                .first()
                .satisfies(allLetters -> {
                    sa.assertThat(allLetters).hasSize(5);
                });

        sa.assertAll();
    }

    @Test
    void builtInGatherers_windowSliding() {

        var result = letters.gather(Gatherers.windowSliding(3))
                .toList();

        log.atInfo().addArgument(result).log("windowSliding letters = [{}]");

        // THEN
        var sa = new SoftAssertions();
        sa.assertThat(result).isNotNull()
                .first()
                .satisfies(allLetters -> {
                    sa.assertThat(allLetters).hasSize(3);
                });

        sa.assertAll();
    }
}
