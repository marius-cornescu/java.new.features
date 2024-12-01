package org.jnew.features.j21;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class Java21ApiUpdatesTest {

    @Test
    void repeatOnStringAndStringBuilder() {
        // given
        var simpleString = "Mojo";
        var builder = new StringBuilder();

        // when
        var repeatString = simpleString.repeat(2);
        var repeatBuilder = builder.repeat("I like to move it, MOVE IT!\n", 3);

        // then
        log.atInfo().log("repeatString = [{}]", repeatString);
        log.atInfo().log("repeatBuilder = [{}]", repeatBuilder);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 50",
            "55, 55",
            "100, 100",
            "170, 170",
            "200, 180",
    })
    void mathClamp_Between_50_and_180(long value, int expectedResult) {
        // given
        int lowerLimit = 50;
        int upperLimit = 180;

        // when
        int actualValue = Math.clamp(value, lowerLimit, upperLimit);

        // then
        log.atInfo().log("mathClamp_Between_50_and_180(): actualValue = [{}]", actualValue);
        assertEquals(expectedResult, actualValue);
    }

}
