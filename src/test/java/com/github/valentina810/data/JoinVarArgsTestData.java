package com.github.valentina810.data;

import com.github.valentina810.data.dto.JoinerData;
import com.github.valentina810.data.dto.Person;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

public class JoinVarArgsTestData {

    static Stream<Arguments> positiveDataProvider() {
        return Stream.of(
                of(new JoinerData.Builder<>()
                        .delimiter("<>")
                        .args(new Integer[]{1, 2, 3})
                        .expectedResult("1<>2<>3")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("/")
                        .args(new String[]{"Abc", "efg"})
                        .expectedResult("Abc/efg")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("/")
                        .args(new String[]{"Abc", "efg"})
                        .expectedResult("Abc/efg")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("")
                        .args(new Integer[]{1, 2})
                        .expectedResult("12")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("+")
                        .args(new Integer[]{1, 2, 8, 9})
                        .expectedResult("1+2+8+9")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("")
                        .args(new String[]{})
                        .expectedResult("")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("/")
                        .args(new String[]{})
                        .expectedResult("")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("/")
                        .args(new Person[]{new Person("Анна"), new Person("Максим")})
                        .expectedResult("Person{name='Анна'}/Person{name='Максим'}")
                        .build())
        );
    }

    static Stream<Arguments> negativeDataProvider() {
        return Stream.of(
                of(new JoinerData.Builder<>()
                        .delimiter("")
                        .args(new Integer[]{1, null})
                        .expectedResult("во входном наборе данных элемент со значением null не допускается")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("")
                        .args(null)
                        .expectedResult("data не может быть null")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(null)
                        .args(new Integer[]{1, 2})
                        .expectedResult("delimiter не может быть null")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(null)
                        .args(null)
                        .expectedResult("data не может быть null")
                        .build())
        );
    }

    private static Stream<Arguments> positiveDataProviderWithToString() {

        return Stream.of(
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{})
                        .expectedResult("")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{"one"})
                        .expectedResult("one")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{"one", "two", "three"})
                        .expectedResult("one, two, three")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(String::valueOf)
                        .args(new Integer[]{1, 2, 3})
                        .expectedResult("1, 2, 3")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter("")
                        .toStringFunction(String::valueOf)
                        .args(new String[]{"one", "two", "three"})
                        .expectedResult("onetwothree")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(i -> i.getClass().getSimpleName().toLowerCase() + "*")
                        .args(new Person[]{new Person("Ольга"), new Person("Алексей")})
                        .expectedResult("person*, person*")
                        .build()),
                of(new JoinerData.Builder<>()
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
                of(new JoinerData.Builder<>()
                        .delimiter(null)
                        .toStringFunction(Object::toString)
                        .args(new String[]{"one", "two"})
                        .expectedResult("delimiter не может быть null")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(Object::toString)
                        .args(null)
                        .expectedResult("data не может быть null")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(Object::toString)
                        .args(new String[]{"one", null, "three"})
                        .expectedResult("во входном наборе данных элемент со значением null не допускается")
                        .build()),
                of(new JoinerData.Builder<>()
                        .delimiter(", ")
                        .toStringFunction(null)
                        .args(new String[]{"one", "two"})
                        .expectedResult("toString не может быть null")
                        .build())
        );
    }
}