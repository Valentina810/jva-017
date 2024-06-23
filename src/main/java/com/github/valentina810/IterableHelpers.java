package com.github.valentina810;

import java.util.Iterator;
import java.util.function.Function;

public final class IterableHelpers {
    private IterableHelpers() {
    }

    public static <T> String join(Iterable<T> data, String delimiter) {
        return join(data, delimiter, Object::toString);
    }

    public static <T> String join(Iterable<T> data, String delimiter, Function<T, String> toString) {
        validateInputs(data, delimiter);
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = data.iterator();
        boolean first = true;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element == null) {
                throw new NullPointerException("во входном наборе данных элемент со значением null не допускается");
            }
            if (!first) {
                sb.append(delimiter);
            }
            sb.append(toString.apply(element));
            first = false;
        }
        return sb.toString();
    }

    private static <T> void validateInputs(Iterable<T> data, String delimiter) {
        if (data == null) {
            throw new NullPointerException("data не может быть null");
        }
        if (delimiter == null) {
            throw new NullPointerException("delimiter не может быть null");
        }
    }
}
