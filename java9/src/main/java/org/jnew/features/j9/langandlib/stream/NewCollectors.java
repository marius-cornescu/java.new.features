package org.jnew.features.j9.langandlib.stream;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.jnew.features.j9.langandlib.Book;

import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class NewCollectors {
    public static void main(String... args) {
        Stream<Book> books = Book.getBooks();

        // Collect all books costing more than 10, grouped by author(s)
        Map<Set<String>, Set<Book>> booksByAuthors =
                books.collect(
                        groupingBy(Book::getAuthors,
                                filtering(b -> b.getPrice() > 10,
                                        toSet())
                        )
                );
        System.out.println(booksByAuthors);

        books = Book.getBooks();
        // Collect all authors that sell books at a given price
        Map<Double, Set<String>> authorsSellingForPrice =
                books.collect(
                        groupingBy(Book::getPrice,
                                flatMapping(b -> b.getAuthors().stream(),
                                        toSet())
                        )
                );
        System.out.println(authorsSellingForPrice);
    }
}
