package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PositionTest {

    @Test
    @DisplayName("체스 판에서 64개의 위치가 생성되어 있다.")
    void size() {
        //when //then
        assertThat(Position.all().size()).isEqualTo(64);
    }

    @ParameterizedTest
    @MethodSource({"generatePosition"})
    @DisplayName("체스 판의 위치를 반환한다.")
    void create(String inputPosition, File file, Rank rank) {
        //given //when
        Position position = Position.from(inputPosition);

        //then
        assertThat(position.getFileSymbol()).isEqualTo(file.getSymbol());
        assertThat(position.getRankNumber()).isEqualTo(rank.getNumber());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9, k1, k0"})
    @DisplayName("체스 판에 존재하지 않는 위치인 경우 예외가 발생한다.")
    void find_exception(String notExistPosition) {
        //given //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Position.from(notExistPosition));
    }

    private static Stream<Arguments> generatePosition() {
        return TestPositions.generate();
    }
}