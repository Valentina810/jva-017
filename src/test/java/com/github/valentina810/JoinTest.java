package com.github.valentina810;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JoinTest {

    @ParameterizedTest(name = "{0},{1} = {2}")
    @MethodSource("com.github.valentina810.data.JoinTestData#positiveDataProvider")
    public void checkPositive(Iterable data, String delimiter, String expected) {
        assertEquals(expected, IterableHelpers.join(data, delimiter));
    }

    @ParameterizedTest(name = "{0},{1} = NullPointerException")
    @MethodSource("com.github.valentina810.data.JoinTestData#negativeDataProvider")
    public void checkNegative(Iterable data, String delimiter, String expected) {
        Exception exception = assertThrows(NullPointerException.class, () -> IterableHelpers.join(data, delimiter));
        assertEquals(expected, exception.getMessage());
    }
}