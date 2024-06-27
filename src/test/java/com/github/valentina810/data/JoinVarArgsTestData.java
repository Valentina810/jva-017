package com.github.valentina810.data;

import com.github.valentina810.data.dto.JoinerData;
import com.github.valentina810.data.dto.Person;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class JoinVarArgsTestData {

    private static Stream<Arguments> positiveDataProviderWithToString() {

        return Stream.of(
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{})
                        .expectedResult("")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{"one"})
                        .expectedResult("one")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{"one", "two", "three"})
                        .expectedResult("one, two, three")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new Integer[]{1, 2, 3})
                        .expectedResult("1, 2, 3")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter("")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{"one", "two", "three"})
                        .expectedResult("onetwothree")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(i -> i.getClass().getSimpleName().toLowerCase() + "*")
                        .args(new Person[]{new Person("Ольга"), new Person("Алексей")})
                        .expectedResult("person*, person*")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new Person[]{})
                        .expectedResult("")
                        .build()
                )
        );
    }

    private static Stream<Arguments> negativeDataProviderWithToString() {
        return Stream.of(
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(null)
                        .toStringFunction(Object::toString)
                        .args(new String[]{"one", "two"})
                        .expectedResult("delimiter не может быть null")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(Object::toString)
                        .args(null)
                        .expectedResult("data не может быть null")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(Object::toString)
                        .args(new String[]{"one", null, "three"})
                        .expectedResult("во входном наборе данных элемент со значением null не допускается")
                        .build()),
                Arguments.of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(null)
                        .args(new String[]{"one", "two"})
                        .expectedResult("toString не может быть null")
                        .build())
        );
    }
}