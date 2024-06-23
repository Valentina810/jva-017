package com.github.valentina810.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

public class JoinWithToStringTestData {
    private static Stream<Arguments> positiveDataProvider() {
        return Stream.of(
                of(Arrays.asList(1, 2, 3), "<>", (Function<Integer, String>) String::valueOf, "1<>2<>3"),
                of(Collections.emptyList(), ", ", (Function<Integer, String>) String::valueOf, ""),
                of(Collections.singletonList(1), ", ", (Function<Integer, String>) String::valueOf, "1"),
                of(Arrays.asList(1, 2, 3), " - ", (Function<Integer, String>) (i -> "Number " + i), "Number 1 - Number 2 - Number 3"),
                of(new LinkedList<>(Arrays.asList(1, 2, 3)), ", ", (Function<Integer, String>) String::valueOf, "1, 2, 3"),
                of(new HashSet<>(Arrays.asList("Abc", "efg")), "/", (Function<String, String>) String::valueOf, "Abc/efg"),
                of(List.of(), ", ", (Function<Integer, String>) String::valueOf, ""),
                of(List.of(1), ", ", (Function<Integer, String>) String::valueOf, "1"),
                of(new ArrayList<>(Arrays.asList(1, 2, 3)), " - ", (Function<Integer, String>) (i -> "Number " + i), "Number 1 - Number 2 - Number 3"),
                of(new TreeSet<>(Arrays.asList(3, 1, 2)), ", ", (Function<Integer, String>) String::valueOf, "1, 2, 3"),
                of(Arrays.asList(1, 2, 3), ", ", (Function<Integer, String>) (i -> "CustomString(" + i + ")"), "CustomString(1), CustomString(2), CustomString(3)")
        );
    }

    private static Stream<Arguments> negativeDataProvider() {
        return Stream.of(
                of(null, ", ", (Function<Integer, String>) String::valueOf, "data не может быть null"),
                of(null, null, (Function<Integer, String>) String::valueOf, "data не может быть null"),
                of(Arrays.asList(1, 2, 3), null, (Function<Integer, String>) String::valueOf, "delimiter не может быть null"),
                of(Arrays.asList(1, null, 3), ", ", (Function<Integer, String>) (i -> "CustomString(" + i + ")"), "во входном наборе данных элемент со значением null не допускается")
        );
    }
}