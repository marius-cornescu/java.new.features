package org.jnew.features.j9.langandlib.stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FindGitConflict {

    public static void main(String... args) throws Exception {

        // Files.lines -> Stream<String>
        try(Stream<String> streamOfLines = Files.lines(Paths.get("index.html"))) {
            streamOfLines
                    .dropWhile(l -> !l.contains("<<<<<<<"))
                    .skip(1)
                    .takeWhile(l -> !l.contains(">>>>>>>"))
                    .forEach(System.out::println);
        }
    }

}
