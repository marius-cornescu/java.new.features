package org.jnew.features.j9.langandlib.optional;

import java.util.Optional;

import org.jnew.features.j9.langandlib.Book;

public class OptionalIfPresentOrElse {

    public static void main(String... args) {
        Optional<Book> full = Optional.of(Book.getBook());

        // Before ifPresentOrElse
        full.ifPresent(System.out::println);

        if (full.isPresent()) {
            System.out.println(full.get());
        } else {
            System.out.println("Nothing here");
        }

        full.ifPresentOrElse(System.out::println,
                () -> System.out.println("Nothing here!"));
        Optional.empty().ifPresentOrElse(System.out::println,
                () -> System.out.println("Nothing here!"));

    }

}
