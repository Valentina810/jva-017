package com.github.valentina810;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JoinWithToStringTest {

    @ParameterizedTest(name = "{0},{1} = {2}")
    @MethodSource("com.github.valentina810.data.JoinWithToStringTestData#positiveDataProvider")
    public <T> void checkPositive(Iterable<T> data, String delimiter, Function<T, String> toString, String expected) {
        assertEquals(expected, IterableHelpers.join(data, delimiter, toString));
    }

    @ParameterizedTest(name = "{0},{1} = NullPointerException")
    @MethodSource("com.github.valentina810.data.JoinWithToStringTestData#negativeDataProvider")
    public <T> void checkNegative(Iterable<T> data, String delimiter, Function<T, String> toString, String expectedMessage) {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> IterableHelpers.join(data, delimiter, toString));
        assertEquals(expectedMessage, exception.getMessage());
    }
}