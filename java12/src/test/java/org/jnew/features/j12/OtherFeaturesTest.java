package org.jnew.features.j12;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        //then
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

        //then
        assertEquals(-1, sameFiles);
        assertEquals(0, differentFiles);
    }
}