package org.jnew.features.j9;

import java.util.List;
import java.util.stream.Collectors;
import java.lang.StackWalker.StackFrame;

public class StackWalkerDemo {

    public static void main(String... args) {
        new StackWalkerDemo().method1();
    }

    public String method1() {
        return method2();
    }

    public static String method2() {
        return method3();
    }

    public static String method3() {
        return method4();
    }

    public static String method4() {
        StackWalker walker = StackWalker.getInstance();

        walker.forEach(System.out::println);

        List<Integer> lines = walker.walk(stackStream ->
            stackStream
              .filter(f -> f.getMethodName().startsWith("m"))
              .map(StackFrame::getLineNumber)
              .collect(Collectors.toList())
        );

        lines.forEach(System.out::println);

        return lines.toString();
    }

}
