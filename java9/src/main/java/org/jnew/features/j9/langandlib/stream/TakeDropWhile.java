package org.jnew.features.j9.langandlib.stream;

import java.util.stream.IntStream;

public class TakeDropWhile {

    public static void main(String... args) {

        // Before takeWhile
        IntStream.range(1, 100)
                .filter(i -> i < 4) // applied to all elements
                .forEach(System.out::println);

        // With takeWhile, only the first 4 elements are
        // evaluated against the predicate.
        IntStream.range(1, 100)
                .takeWhile(i -> i < 4) // short-circuits on element '4'
                .forEach(System.out::println);


        // Before dropWhile
        IntStream.range(1, 10)
                .filter(i -> i >= 4)
                .forEach(System.out::println);

        // With dropWhile (only works if Stream is sorted!)
        IntStream.range(1, 10)
                .dropWhile(i -> i < 4)
                .forEach(System.out::println);
    }

}
