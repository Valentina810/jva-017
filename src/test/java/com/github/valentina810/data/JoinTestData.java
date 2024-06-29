package com.github.valentina810.data;

import com.github.valentina810.data.dto.JoinerData;
import com.github.valentina810.data.dto.JoinerData.Builder;
import com.github.valentina810.data.dto.Person;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.params.provider.Arguments.of;

public class JoinTestData {
    static Stream<Arguments> positiveDataProvider() {
        return Stream.of(
                of(new Builder<>()
                        .delimiter("<>")
                        .data(new ArrayList<>(asList(1, 2, 3)))
                        .expectedResult("1<>2<>3")
                        .build()),
                of(new Builder<>()
                        .delimiter("/")
                        .data(new HashSet<>(asList("Abc", "efg")))
                        .expectedResult("Abc/efg")
                        .build()),
                of(new Builder<>()
                        .delimiter("/")
                        .data(new PriorityQueue<>(asList("Abc", "efg")))
                        .expectedResult("Abc/efg")
                        .build()),
                of(new Builder<>()
                        .delimiter("")
                        .data(new Vector<>(asList(1, 2)))
                        .expectedResult("12")
                        .build()),
                of(new Builder<>()
                        .delimiter("+")
                        .data(new TreeSet<>(asList(1, 2, 8, 9)))
                        .expectedResult("1+2+8+9")
                        .build()),
                of(new Builder<>()
                        .delimiter("")
                        .data(List.of())
                        .expectedResult("")
                        .build()),
                of(new Builder<>()
                        .delimiter("/")
                        .data(List.of())
                        .expectedResult("")
                        .build()),
                of(new Builder<>()
                        .delimiter("/")
                        .data(List.of(new Person("Анна"), new Person("Максим")))
                        .expectedResult("Person{name='Анна'}/Person{name='Максим'}")
                        .build())
        );
    }

    static Stream<Arguments> negativeDataProvider() {
        return Stream.of(
                of(new Builder<>()
                        .delimiter("")
                        .data(new ArrayList<>(asList(1, null)))
                        .expectedResult("во входном наборе данных элемент со значением null не допускается")
                        .build()),
                of(new Builder<>()
                        .delimiter("")
                        .data(null)
                        .expectedResult("data не может быть null")
                        .build()),
                of(new Builder<>()
                        .delimiter(null)
                        .data(List.of(1, 2))
                        .expectedResult("delimiter не может быть null")
                        .build()),
                of(new Builder<>()
                        .delimiter(null)
                        .data(null)
                        .expectedResult("data не может быть null")
                        .build())
        );
    }

    private static Stream<Arguments> positiveDataProviderWithToString() {
        return Stream.of(
                of(new JoinerData.Builder<>()
                        .delimiter("<>")
                        .toStringFunction(String::valueOf)
                        .data(Arrays.asList(1, 2, 3))
                        .expectedResult("1<>2<>3")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .data(Collections.emptyList())
                        .expectedResult("")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .data(Collections.singletonList(1))
                        .expectedResult("1")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(" - ")
                        .toStringFunction(i -> "Number " + i)
                        .data(Arrays.asList(1, 2, 3))
                        .expectedResult("Number 1 - Number 2 - Number 3")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .data(new LinkedList<>(Arrays.asList(1, 2, 3)))
                        .expectedResult("1, 2, 3")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("/")
                        .toStringFunction(String::valueOf)
                        .data(new HashSet<>(Arrays.asList("Abc", "efg")))
                        .expectedResult("Abc/efg")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .data(List.of())
                        .expectedResult("")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .data(List.of(1))
                        .expectedResult("1")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(" - ")
                        .toStringFunction(i -> "Number " + i)
                        .data(new ArrayList<>(Arrays.asList(1, 2, 3)))
                        .expectedResult("Number 1 - Number 2 - Number 3")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .data(new TreeSet<>(Arrays.asList(3, 1, 2)))
                        .expectedResult("1, 2, 3")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(i -> "CustomString(" + i + ")")
                        .expectedResult("CustomString(1), CustomString(2), CustomString(3)")
                        .data(Arrays.asList(1, 2, 3))
                        .build()),
                of(new Builder<>()
                        .delimiter("|")
                        .toStringFunction(i -> "*" + i.getClass().getSimpleName() + "*")
                        .data(List.of(new Person("Анна"), new Person("Максим")))
                        .expectedResult("*Person*|*Person*")
                        .build())
        );
    }

    private static Stream<Arguments> negativeDataProviderWithToString() {
        return Stream.of(
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .data(null)
                        .expectedResult("data не может быть null")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(null)
                        .toStringFunction(String::valueOf)
                        .data(null)
                        .expectedResult("data не может быть null")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(null)
                        .toStringFunction(String::valueOf)
                        .data(Arrays.asList(1, 2, 3))
                        .expectedResult("delimiter не может быть null")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(i -> "CustomString(" + i + ")")
                        .data(Arrays.asList(1, null, 3))
                        .expectedResult("во входном наборе данных элемент со значением null не допускается")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(null)
                        .data(Arrays.asList(1, 2, 3))
                        .expectedResult("toString не может быть null")
                        .build())
        );
    }
}