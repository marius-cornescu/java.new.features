package org.jnew.features.j12;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherFeaturesTest {

    @Test
    void collectorsTeeing() {
        // given
        var numbers = Stream.of(10, 15, 20, 25, 30, 35, 40);

        // when
        long actual = numbers
                .collect(Collectors.teeing(
                                Collectors.summingInt(Integer::valueOf),
                                Collectors.counting(),
                                (sum, count) -> sum / count
                        )
                );

        // then
        assertEquals(25L, actual);
    }

    @Test
    void filesMismatch() throws IOException {
        // given

        // when
        long sameFiles = Files.mismatch(
                Path.of("build/resources/test/first_text.txt"),
                Path.of("build/resources/test/first_text.txt")
        );

        long differentFiles = Files.mismatch(
                Path.of("build/resources/test/first_text.txt"),
                Path.of("build/resources/test/second_text.txt")
        );

        // then
        assertEquals(-1, sameFiles);
        assertEquals(0, differentFiles);
    }

    /**
     * public static void main(String... args) {
     *  switch (1) {
     *      case 1:
     *          break;
     *      case 2:
     *          break;
     *  }
     * }
     * <p>
     * public static void main(java.lang.String[]);
     * Code:
     *  Stack=1, Locals=1, Args_size=1
     *      0:   iconst_1
     *      1:   lookupswitch{ //2
     *              1: 28;
     *              2: 31;
     *              default: 31 }
     *      28:  goto    31
     *      31:  return
     *
     * switches have O(1) lookup
     *
     */
    @Test
    void switchExpression() {
        // given
        DayOfWeek source = FRIDAY;

        // when
        var actual = switch (source) {
            case MONDAY -> 20L;

            case FRIDAY -> 30L;

            default -> "PARTY - Y because I got to".hashCode();
        };

        // then
        assertEquals(30L, actual);
    }

    @Test
    void switchWithMap() {
        // given
        DayOfWeek source = FRIDAY;

        // - STRATEGY pattern
        Map<DayOfWeek, Number> rules = new HashMap<>(){{
                put(MONDAY, 20L);
                put(FRIDAY, 30L);
                put(null, "PARTY - Y because I got to".hashCode());
        }};

        // when
        var actual = rules.getOrDefault(source, rules.get(null));

        // then
        assertEquals(30L, actual);
    }
}