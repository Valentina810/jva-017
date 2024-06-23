package com.github.valentina810.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.List.of;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class JoinTestData {
    static Stream<Arguments> positiveDataProvider() {
        return Stream.of(
                arguments(new ArrayList<>(asList(1,2,3)), "<>", "1<>2<>3"),
                arguments(new HashSet<>(asList("Abc", "efg")), "/", "Abc/efg"),
                arguments(new PriorityQueue<>(asList("Abc", "efg")), "/", "Abc/efg"),
                arguments(new Vector<>(asList(1, 2)), "", "12"),
                arguments(new TreeSet<>(asList(1, 2,8,9)), "+", "1+2+8+9"),
                arguments(of(), "", ""),
                arguments(of(), "/", ""),
                arguments(of(1), "/", "1")
        );
    }

    static Stream<Arguments> negativeDataProvider() {
        return Stream.of(
                arguments(new ArrayList<>(asList(1,null)), "", "во входном наборе данных элемент со значением null не допускается"),
                arguments(null, "", "data не может быть null"),
                arguments(of(1, 2), null, "delimiter не может быть null"),
                arguments(null, null, "data не может быть null")
        );
    }
}