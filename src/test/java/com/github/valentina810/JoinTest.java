package com.github.valentina810;

import com.github.valentina810.data.dto.JoinerData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JoinTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.github.valentina810.data.JoinTestData#positiveDataProvider")
    public void checkPositive(JoinerData joinerData) {
        assertEquals(joinerData.getExpectedResult(), Joiner.join(joinerData.getDelimiter(), joinerData.getData()));
    }

    @ParameterizedTest(name = "{0} = NullPointerException")
    @MethodSource("com.github.valentina810.data.JoinTestData#negativeDataProvider")
    public void checkNegative(JoinerData joinerData) {
        assertThatThrownBy(() -> Joiner.join(joinerData.getDelimiter(), joinerData.getData()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(joinerData.getExpectedResult());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("com.github.valentina810.data.JoinTestData#positiveDataProviderWithToString")
    public void checkPositiveWithToString(JoinerData joinerData) {
        assertEquals(joinerData.getExpectedResult(), Joiner.join(joinerData.getDelimiter(),
                joinerData.getToStringFunction(), joinerData.getData()));
    }

    @ParameterizedTest(name = "{0} = NullPointerException")
    @MethodSource("com.github.valentina810.data.JoinTestData#negativeDataProviderWithToString")
    public void checkNegativeWithToString(JoinerData joinerData) {
        assertThatThrownBy(() -> Joiner.join(joinerData.getDelimiter(), joinerData.getToStringFunction(), joinerData.getData()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(joinerData.getExpectedResult());
    }
}