package org.jnew.features.j11;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;

public class OtherMethods {

    public static void main(@Nullable String[] args) {
        // Optional::isEmpty
        var opt = Optional.ofNullable(null);
        System.out.println("This is empty = " + opt.isEmpty());

        // Predicate::not
        var multilineText = "first\nsecond\nthird\nlast\n";
        multilineText.lines()
                .filter(Predicate.not(String::isBlank))
                .forEach(System.out::println);


    }

}
