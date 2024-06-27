package com.github.valentina810;

import com.github.valentina810.data.dto.JoinerData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinVarArgsTest {
    @ParameterizedTest(name = "{0},{1} = {2}")
    @MethodSource("com.github.valentina810.data.JoinVarArgsTestData#positiveDataProviderWithToString")
    public void checkPositiveWithToString(JoinerData joinerData) {
        assertEquals(joinerData.getExpectedResult(), Joiner.join(joinerData.getDelimiter(),
                joinerData.getToStringFunction(), joinerData.getArgs()));
    }

    @ParameterizedTest(name = "{0},{1} = NullPointerException")
    @MethodSource("com.github.valentina810.data.JoinVarArgsTestData#negativeDataProviderWithToString")
    public void checkNegativeWithToString(JoinerData joinerData) {
        assertThatThrownBy(() -> Joiner.join(joinerData.getDelimiter(), joinerData.getToStringFunction(), joinerData.getArgs()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(joinerData.getExpectedResult());
    }
}