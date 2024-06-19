package com.github.valentina810;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericHelpersTest {

    @ParameterizedTest(name = "{0},{1} = {2}")
    @MethodSource("com.github.valentina810.data.TestData#positiveDataProvider")
    public void checkPositive(List data, String delimiter, String expected) {
        List source = new ArrayList(data);
        Assertions.assertAll(
                () -> assertEquals(expected, GenericHelpers.join(data, delimiter)),
                () -> assertEquals(source, data)
        );
    }

    @ParameterizedTest(name = "{0},{1} = NullPointerException")
    @MethodSource("com.github.valentina810.data.TestData#negativeDataProvider")
    public void checkNegative(List data, String delimiter, String expected) {
        Exception exception = assertThrows(NullPointerException.class, () -> GenericHelpers.join(data, delimiter));
        assertTrue(exception.getMessage().contains(expected));
    }
}