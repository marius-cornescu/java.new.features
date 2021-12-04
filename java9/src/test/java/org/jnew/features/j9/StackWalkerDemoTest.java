package org.jnew.features.j9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StackWalkerDemoTest {

    @Test
    void testGetMessage() {
        // Given
        StackWalkerDemo stackWalkerDemo = new StackWalkerDemo();

        // When
        String stackText = stackWalkerDemo.method1();

        // Then
        assertEquals("[30, 22, 18, 14, 54]", stackText);
    }
}
