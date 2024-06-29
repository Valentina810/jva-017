package com.github.valentina810.data.dto;

import com.github.valentina810.Stringer;

import java.util.Arrays;

public class JoinerData<T> {
    private String delimiter;
    private Stringer<T, String> toStringFunction;
    private Iterable<T> data;
    private T[] args;
    private String expectedResult;

    private JoinerData() {
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private String delimiter;
        private Stringer<T, String> toStringFunction;
        private Iterable<T> data;
        private T[] args;
        private String expectedResult;

        public Builder<T> delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder<T> toStringFunction(Stringer<T, String> toStringFunction) {
            this.toStringFunction = toStringFunction;
            return this;
        }

        public Builder<T> data(Iterable<T> data) {
            this.data = data;
            return this;
        }

        @SafeVarargs
        public final Builder<T> args(T... args) {
            this.args = args;
            return this;
        }

        public Builder<T> expectedResult(String expectedResult) {
            this.expectedResult = expectedResult;
            return this;
        }

        public JoinerData<T> build() {
            JoinerData<T> joinerData = new JoinerData<>();
            joinerData.delimiter = this.delimiter;
            joinerData.toStringFunction = this.toStringFunction;
            joinerData.data = this.data;
            joinerData.args = this.args;
            joinerData.expectedResult = this.expectedResult;
            return joinerData;
        }
    }

    public String getDelimiter() {
        return delimiter;
    }

    public Stringer<T, String> getToStringFunction() {
        return toStringFunction;
    }

    public Iterable<T> getData() {
        return data;
    }

    public T[] getArgs() {
        return args;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        String isStringFunction = toStringFunction == null ? "null" : "задана";
        return "JoinerData{" +
                "delimiter='" + delimiter + '\'' +
                ", toStringFunction=" + isStringFunction +
                ", data=" + data +
                ", args=" + Arrays.toString(args) +
                ", expectedResult='" + expectedResult + '\'' +
                '}';
    }
}