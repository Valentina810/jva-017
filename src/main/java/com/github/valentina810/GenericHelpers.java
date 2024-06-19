package com.github.valentina810;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class GenericHelpers {
    public static <T> String join(List<T> data, String delimiter) {
        if (data.isEmpty()) {
            return "";
        } else if (data.size() == 1) {
            return data.get(0).toString();
        } else {
            return data.stream()
                    .map(Object::toString)
                    .collect(joining(delimiter));
        }
    }
}