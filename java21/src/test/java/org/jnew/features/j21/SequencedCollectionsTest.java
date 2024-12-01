package org.jnew.features.j21;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SequencedCollectionsTest {

    @ParameterizedTest
    @CsvSource({
            "A, B",
            "C, D",
    })
    void test(String value, String expectedResult) {
        // given

        // when

        // then
    }

}
