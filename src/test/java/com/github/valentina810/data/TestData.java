package com.github.valentina810.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static java.util.List.of;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestData {
    static Stream<Arguments> positiveDataProvider() {
        return Stream.of(
                arguments(of(1, 2, 3), "<>", "1<>2<>3"),
                arguments(of("Abc", "efg"), "/", "Abc/efg"),
                arguments(of(1, 2), "", "12"),
                arguments(of(), "", ""),
                arguments(of(), "/", ""),
                arguments(of(1), "/", "1")
        );
    }

    static Stream<Arguments> negativeDataProvider() {
        return Stream.of(
                arguments(null, "", "is null"),
                arguments(of(1, 2), null, "The delimiter must not be null"),
                arguments(null, null, "is null")
        );
    }
}