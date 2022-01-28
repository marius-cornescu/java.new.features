package org.jnew.features.j12;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CompactNumbersFormatTest {

    @Test
    void compactNumberFormat() {
        // given
        NumberFormat shortNF = NumberFormat.getCompactNumberInstance();
        NumberFormat shortNF2 = NumberFormat.getCompactNumberInstance();
        shortNF2.setMaximumFractionDigits(2);

        // when
        String actual1000 = shortNF.format(1_000);
        String actual1Mil = shortNF.format(1_000_000);
        String actual2000 = shortNF.format(1_500);
        String actual1k5 = shortNF2.format(1_500);

        //then
        assertEquals("1K", actual1000);
        assertEquals("1M", actual1Mil);
        assertEquals("2K", actual2000);
        assertEquals("1.5K", actual1k5);
    }

    @Test
    void compactNumberFormatGermanLocale() {
        // given
        NumberFormat shortNF = NumberFormat.getCompactNumberInstance(Locale.GERMAN, NumberFormat.Style.SHORT);
        NumberFormat shortNF2 = NumberFormat.getCompactNumberInstance();
        shortNF2.setMaximumFractionDigits(2);

        // when
        String actual1000 = shortNF.format(1_000);
        String actual1Mil = shortNF.format(1_000_000);
        String actual2000 = shortNF.format(1_500);
        String actual1k5 = shortNF2.format(1_500);

        //then
        assertEquals("1.000", actual1000);
        assertTrue(actual1Mil.endsWith("Mio."));
        assertEquals("1.500", actual2000);
        assertEquals("1.5K", actual1k5);
    }

}