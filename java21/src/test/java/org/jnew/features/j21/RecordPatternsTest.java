package org.jnew.features.j21;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.of;

@Slf4j
class RecordPatternsTest {

    @Test
    void testGetRecordComponents() {
        // given
        record Person(String name, int age) {
        }

        var person = new Person("Man", 25);

        // when
        if (person instanceof Person(String name, int age)) {
            assertNotNull(name);
            assertEquals(25, age);
        }

        if (person instanceof Person(var name, var age)) {
            assertNotNull(name);
            assertEquals(25, age);
        }

        // then
    }

    record Point(int x, int y) {
    }

    sealed interface Shape {
    }

    record Circle(Point center, int radius) implements Shape {
    }

    record Rectangle(Point topLeft, Point bottomRight) implements Shape {
    }

    record Square(Point topLeft, int length) implements Shape {
    }

    static Stream<Arguments> variousShapes() {
        return Stream.of(
                of("Total area of 3 shapes",
                        Stream.of(
                                new Rectangle(new Point(1, 2), new Point(4, 6)),
                                new Circle(new Point(0, 0), 6),
                                new Square(new Point(1, 2), 6)
                        ),
                        161.09733552923257)
        );
    }

    private static double areaV1(Shape shape) {
        return switch (shape) {
            case Circle(var __, var radius) -> Math.PI * radius * radius;
            case Rectangle(var ul, var br) -> Math.abs((br.x() - ul.x()) * (ul.y() - br.y()));
            case Square(var __, var len) -> len * len;
        };
    }

    private static double areaV2(Shape shape) {
        return switch (shape) {
            case Circle(var __, var radius) -> Math.PI * radius * radius;
            case Rectangle(Point(var ulx, var uly), Point(var brx, var bry)) -> Math.abs((brx - ulx) * (uly - bry));
            case Square(var __, var len) -> len * len;
        };
    }

    private static double areaV3(Shape shape) {
        return switch (shape) {
            case Circle(var __, var radius) -> Math.PI * radius * radius;
            case Rectangle(Point(var ulx, var uly), Point(var brx, var bry)) -> Math.abs((brx - ulx) * (uly - bry));
            case Square(Point(var x, var y), var len) when x > 0 && y > 0 -> len * len;
            case Square other -> 0;
        };
    }

    @ParameterizedTest(name = "Test {index}: ({0}) = {2} minutes")
    @MethodSource("variousShapes")
    void testCalculateAreaOfShapesV1(String name, Stream<Shape> shapes, double expectedValue) {
        // given

        // when
        var actualValue = shapes.
                mapToDouble(RecordPatternsTest::areaV1)
                .sum();

        // then
        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest(name = "Test {index}: ({0}) = {2} minutes")
    @MethodSource("variousShapes")
    void testCalculateAreaOfShapesV2(String name, Stream<Shape> shapes, double expectedValue) {
        // given

        // when
        var actualValue = shapes.
                mapToDouble(RecordPatternsTest::areaV2)
                .sum();

        // then
        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest(name = "Test {index}: ({0}) = {2} minutes")
    @MethodSource("variousShapes")
    void testCalculateAreaOfShapesV3(String name, Stream<Shape> shapes, double expectedValue) {
        // given

        // when
        var actualValue = shapes.
                mapToDouble(RecordPatternsTest::areaV3)
                .sum();

        // then
        assertEquals(expectedValue, actualValue);
    }

}
