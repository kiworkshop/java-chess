package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @MethodSource({"generatePosition"})
    @DisplayName("체스 판의 위치를 반환한다.")
    void create(String inputPosition, File file, Rank rank) {
        //given //when
        Position position = Position.from(inputPosition);

        //then
        assertThat(position.getFile()).isEqualTo(file.value());
        assertThat(position.getRank()).isEqualTo(rank.value());
    }

    private static Stream<Arguments> generatePosition() {
        return TestPositions.generate();
    }

}