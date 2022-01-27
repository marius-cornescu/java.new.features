package org.jnew.features.j12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringMethodsTest {

    @Test
    void indentMethodSingle() {
        // given
        var singleText = "ha ";

        // when
        String actual = singleText.indent(5);

        //then
        assertEquals("     ha \n", actual);
    }

    @Test
    void indentMethodMultiline() {
        // given
        var singleText = "This \nIs\r\nA\nMixed\r\nText\n";

        // when
        String actual = singleText.indent(5);

        //then
        assertEquals("     This \n" +
                "     Is\n" +
                "     A\n" +
                "     Mixed\n" +
                "     Text\n", actual);
    }

}