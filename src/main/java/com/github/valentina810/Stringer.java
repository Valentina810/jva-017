package com.github.valentina810;

@FunctionalInterface
public interface Stringer<T, R> {
    R toString(T t);
}