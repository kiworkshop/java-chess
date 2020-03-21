package chess.domain;

import chess.domain.position.ICoordinate;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @MethodSource("validPositionScenarioArguments")
    void validPosition(ICoordinate x, ICoordinate y, boolean expected) {
        //when
        boolean validity = Position.of(x, y).isValid();
        //then
        assertThat(validity).isEqualTo(expected);
    }

    private static Stream<Arguments> validPositionScenarioArguments() {
        ICoordinate validCoordinate = () -> true;
        ICoordinate invalidCoordinate = () -> false;

        return Stream.of(
                Arguments.of(validCoordinate, validCoordinate, true),
                Arguments.of(validCoordinate, invalidCoordinate, false),
                Arguments.of(invalidCoordinate, validCoordinate, false),
                Arguments.of(invalidCoordinate, invalidCoordinate, false)
        );
    }
}