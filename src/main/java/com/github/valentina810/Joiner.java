package com.github.valentina810;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

/**
 * Utility class providing methods to join elements of an iterable or an
 * array into a single string with a specified delimiter.
 */
public final class Joiner {
    private static final String EMPTY_STRING = "";

    private Joiner() {
    }

    /**
     * Joins the elements of the given iterable into a single string with the specified delimiter.
     * Uses the {@code toString} method of each element to convert it to a string.
     *
     * @param delimiter the delimiter to separate each element
     * @param data      the iterable containing the elements to join
     * @param <T>       the type of elements in the iterable
     * @return a string consisting of the joined elements separated by the delimiter
     * @throws NullPointerException if {@code delimiter} or {@code data} is {@code null}, or if any element in {@code data} is {@code null}
     */
    public static <T> String join(String delimiter, Iterable<T> data) {
        return join(delimiter, Object::toString, data);
    }

    /**
     * Joins the elements of the given iterable into a single string with the specified delimiter.
     * Uses the provided {@code toStringFunction} to convert each element to a string.
     *
     * @param delimiter        the delimiter to separate each element
     * @param toStringFunction a function to convert each element to a string
     * @param data             the iterable containing the elements to join
     * @param <T>              the type of elements in the iterable
     * @return a string consisting of the joined elements separated by the delimiter
     * @throws NullPointerException if {@code delimiter}, {@code data}, or {@code toStringFunction} is {@code null}, or if any element in {@code data} is {@code null}
     */
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

    /**
     * Joins the elements of the given array into a single string with the specified delimiter.
     * Uses the {@code toString} method of each element to convert it to a string.
     *
     * @param delimiter the delimiter to separate each element
     * @param data      the array containing the elements to join
     * @param <T>       the type of elements in the array
     * @return a string consisting of the joined elements separated by the delimiter
     * @throws NullPointerException if {@code delimiter} or {@code data} is {@code null}, or if any element in {@code data} is {@code null}
     */
    @SafeVarargs
    public static <T> String join(String delimiter, T... data) {
        return join(delimiter, Object::toString, data);
    }

    /**
     * Joins the elements of the given array into a single string with the specified delimiter.
     * Uses the provided {@code toStringFunction} to convert each element to a string.
     *
     * @param delimiter        the delimiter to separate each element
     * @param toStringFunction a function to convert each element to a string
     * @param data             the array containing the elements to join
     * @param <T>              the type of elements in the array
     * @return a string consisting of the joined elements separated by the delimiter
     * @throws NullPointerException if {@code delimiter}, {@code data}, or {@code toStringFunction} is {@code null}, or if any element in {@code data} is {@code null}
     */
    @SafeVarargs
    public static <T> String join(String delimiter, Stringer<T, String> toStringFunction, T... data) {
        requireNonNull(data, "data не может быть null");
        return join(delimiter, toStringFunction, asList(data));
    }

    /**
     * Validates the inputs for the join methods.
     *
     * @param data             the iterable containing the elements to join
     * @param delimiter        the delimiter to separate each element
     * @param toStringFunction a function to convert each element to a string
     * @param <T>              the type of elements in the iterable
     * @throws NullPointerException if {@code delimiter}, {@code data}, or {@code toStringFunction} is {@code null}
     */
    private static <T> void validateInputs(Iterable<T> data, String delimiter, Stringer<T, String> toStringFunction) {
        requireNonNull(data, "data не может быть null");
        requireNonNull(delimiter, "delimiter не может быть null");
        requireNonNull(toStringFunction, "toString не может быть null");
    }
}
