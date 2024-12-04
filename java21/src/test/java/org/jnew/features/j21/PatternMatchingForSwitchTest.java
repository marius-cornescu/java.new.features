package org.jnew.features.j21;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

@Slf4j
class PatternMatchingForSwitchTest {

    static Stream<Arguments> variousListsOfTypes() {
        return Stream.of(
                of("60 minutes + 10 minutes" ,List.of(new AType(3600), new BType(10)), 70),
                of("1 minute + 1 minute", List.of(new AType(60), new BType(1)), 2)
        );
    }

    @ParameterizedTest(name = "Test {index}: ({0}) = {2} minutes")
    @MethodSource("variousListsOfTypes")
    void calculateTotalDurationInMinutes(String name, List<DefaultType> types, long expectedValue) {
        // given
        ToLongFunction<DefaultType> toMinutes = (DefaultType value) -> switch (value) {
            case AType a -> a.getTimeInSeconds() / 60;
            case BType b -> b.getTimeInMinutes();
            case null -> 0;
            default -> throw new NoSuchElementException("TIAB: Type is not known");
        };

        // when
        var totalMinutes = types.stream()
                .mapToLong(toMinutes)
                .sum();

        // then
        assertEquals(expectedValue, totalMinutes);
    }

    static Stream<Arguments> variousListsOfSealedTypes() {
        return Stream.of(
                of("60 minutes + 10 minutes" ,List.of(new ASealedType(3600), new BSealedType(10)), 70),
                of("1 minute + 1 minute", List.of(new ASealedType(60), new BSealedType(1)), 2)
        );
    }

    @ParameterizedTest(name = "Test {index}: ({0}) = {2} minutes")
    @MethodSource("variousListsOfSealedTypes")
    void calculateTotalDurationInMinutesForSealed(String name, List<DefaultSealedType> types, long expectedValue) {
        // given
        ToLongFunction<DefaultSealedType> toMinutes = (DefaultSealedType value) -> switch (value) {
            case ASealedType a -> a.getTimeInSeconds() / 60;
            case BSealedType b -> b.getTimeInMinutes();
            case null -> 0;
            //default -> throw new NoSuchElementException("TIAB: Type is not known"); // no longer needed
        };

        // when
        var totalMinutes = types.stream()
                .mapToLong(toMinutes)
                .sum();

        // then
        assertEquals(expectedValue, totalMinutes);
    }

}
