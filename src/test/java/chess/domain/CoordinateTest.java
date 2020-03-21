package chess.domain;

import chess.domain.position.Coordinate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void validCoordinate(int value) {
        assertThat(Coordinate.of(value).isValid()).isTrue();
    }

    @ParameterizedTest()
    @ValueSource(ints = {-2, -1, 8, 9})
    void invalidCoordinate(int value) {
        assertThat(Coordinate.of(value).isValid()).isFalse();
    }

    @ParameterizedTest()
    @MethodSource("provideEveryCoordniates")
    void validateCoordinate(int value, boolean expected) {
        assertThat(Coordinate.of(value).isValid()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideEveryCoordniates() {
        return Stream.of(
                Arguments.of(-2, false),
                Arguments.of(-1, false),
                Arguments.of(0, true),
                Arguments.of(1, true),
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(4, true),
                Arguments.of(5, true),
                Arguments.of(6, true),
                Arguments.of(7, true),
                Arguments.of(8, false),
                Arguments.of(9, false)
        );
    }
}