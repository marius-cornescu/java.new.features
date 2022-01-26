package org.jnew.features.j11;

public class StringMethods {

    public static void main(String[] args) {
        // REPEAT
        var singleText = "ha ";
        System.out.println(singleText.repeat(10) + "The Man");

        // isBlank
        var emptyText = "     ";
        System.out.println("Are you blank? = " + emptyText.isEmpty());

        // strip
        var textWithSpaces = "\r\n     TEXT TO USE     \u2005";

        System.out.println("TRIM = [" + textWithSpaces.trim() + "]");
        System.out.println("STRIP = [" + textWithSpaces.strip() + "]");

        // LINE
        var multilineText = "first\nsecond\nthird\nlast\n";
        System.out.println("HAS THIRD = " + multilineText.lines().anyMatch("third"::equals));

    }

}
