package org.jnew.features.j21;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

@Slf4j
class SequencedCollectionsTest {

    static Stream<Arguments> variousCollectionValues() {
        var list = new ArrayList<>();
        list.add("FIRST");
        list.add("SECOND");
        list.add("LAST");

        var dequeue = new LinkedList<>();
        dequeue.add("FIRST");
        dequeue.add("SECOND");
        dequeue.add("LAST");

        var sortedSet = new TreeSet<>();
        sortedSet.add("FIRST");
        sortedSet.add("SECOND");
        sortedSet.add("LAST");

        return Stream.of(
                of(list, "LAST"),
                of(dequeue, "LAST"),
                of(dequeue.reversed(), "FIRST"),
                of(sortedSet, "SECOND")
        );
    }

    @ParameterizedTest
    @MethodSource("variousCollectionValues")
    void lastElementOfCollectionsIs(SequencedCollection<String> collection, String expectedResult) {
        // given

        // when
        var lastElement = collection.getLast();

        // then
        assertEquals(expectedResult, lastElement);
    }

}
