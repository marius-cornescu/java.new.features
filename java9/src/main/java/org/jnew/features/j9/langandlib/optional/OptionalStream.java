package org.jnew.features.j9.langandlib.optional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jnew.features.j9.langandlib.Book;

public class OptionalStream {

    public static void main(String[] args) {

        Stream<Optional<Integer>> optionals =
                Stream.of(Optional.of(1), Optional.empty(), Optional.of(2));

        Stream<Integer> ints = optionals.flatMap(Optional::stream);

        ints.forEach(System.out::println);

        // Find all first authors of the books
        Set<String> authors = Book.getBooks()
                .map(book -> book.authors.stream().findFirst())
                .flatMap(optAuthor -> optAuthor.stream())
                .collect(Collectors.toSet());
        System.out.println(authors);
    }
}
