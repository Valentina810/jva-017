package com.github.valentina810;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public final class Joiner {
    private static final String EMPTY_STRING = "";

    private Joiner() {
    }

    public static <T> String join(String delimiter, Iterable<T> data) {
        return join(delimiter, Object::toString, data);
    }

    public static <T> String join(String delimiter, Stringer<T, String> toStringFunction, Iterable<T> data) {
        validateInputs(data, delimiter, toStringFunction);
        if (data.iterator().hasNext()) {
            StringBuilder sb = new StringBuilder();
            Iterator<T> iterator = data.iterator();
            boolean first = true;
            while (iterator.hasNext()) {
                T element = iterator.next();
                requireNonNull(element, "во входном наборе данных элемент со значением null не допускается");
                if (!first) {
                    sb.append(delimiter);
                }
                sb.append(toStringFunction.toString(element));
                first = false;
            }
            return sb.toString();
        } else return EMPTY_STRING;
    }

    @SafeVarargs
    public static <T> String join(String delimiter, T... data) {
        return join(delimiter, Object::toString, data);
    }

    @SafeVarargs
    public static <T> String join(String delimiter, Stringer<T, String> toStringFunction, T... data) {
        requireNonNull(data, "data не может быть null");
        return join(delimiter, toStringFunction, asList(data));
    }

    private static <T> void validateInputs(Iterable<T> data, String delimiter, Stringer<T, String> toStringFunction) {
        requireNonNull(data, "data не может быть null");
        requireNonNull(delimiter, "delimiter не может быть null");
        requireNonNull(toStringFunction, "toString не может быть null");
    }
}
