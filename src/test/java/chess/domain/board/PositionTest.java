package chess.domain.board;

import chess.domain.piece.move.Gap;
import chess.domain.piece.move.MoveUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PositionTest {

    @Test
    @DisplayName("가로, 세로 인자에 해당하는 위치를 반환한다.")
    void from_file_and_rank() {
        //given
        File file = File.a;
        Rank rank = Rank.R1;

        //when
        Position position = Position.from(file, rank);

        //then
        assertThat(position).extracting("file", "rank")
                .containsOnly(file, rank);
    }

    @Test
    @DisplayName("문자열 키에 해당하는 위치를 반환한다.")
    void of_valid_key() {
        //given
        File file = File.a;
        Rank rank = Rank.R1;
        String key = file.name() + rank.getIndex();

        //when
        Position position = Position.of(key);

        //then
        assertThat(position).extracting("file", "rank")
                .containsOnly(file, rank);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a0", "j1"})
    @DisplayName("유효하지 않은 키로 검색하면 예외를 던진다.")
    void of_invalid_key(String key) {
        //given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Position.of(key))
                .withMessage("위치 입력값이 잘못되었습니다.");
    }

    @Test
    @DisplayName("모든 위치 이름을 반환한다.")
    void names() {
        //given, when
        Collection<String> names = Position.names();

        //then
        assertThat(names).hasSize(64);
    }

    @Test
    @DisplayName("두 위치가 주어지면 좌표 차이(target-source)를 계산한다.")
    void calculate_gap() {
        // given
        Position source = Position.of("a1");
        Position target = Position.of("f3");

        // when
        Gap gap = target.calculateGap(source);

        // then
        assertThat(gap.file()).isEqualTo(5);
        assertThat(gap.rank()).isEqualTo(2);
    }

    @Test
    @DisplayName("거쳐가는 모든 위치를 반환한다.")
    void find_passing_positions() {
        // given
        Position source = Position.of("d4");
        Position target = Position.of("d7");
        MoveUnit moveUnit = MoveUnit.NORTH;

        // when
        Collection<Position> positions = source.findPassingPositions(target, moveUnit);

        // then
        assertThat(positions)
                .hasSize(2)
                .containsExactlyInAnyOrder(Position.of("d5"), Position.of("d6"));
    }

    @Test
    @DisplayName("이동 가능한 모든 위치를 반환한다.")
    void find_reachable_positions() {
        // given
        Position source = Position.of("d4");
        MoveUnit moveUnit = MoveUnit.SOUTH;
        List<Position> expected = Arrays.asList(Position.of("d3"), Position.of("d2"), Position.of("d1"));

        // when
        List<Position> reachablePositions = source.findReachablePositions(moveUnit);

        // then
        assertThat(reachablePositions).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"d4, NORTH, true", "h2, EAST, false"})
    @DisplayName("주어진 좌표만큼 이동 가능한지 확인한다.")
    void is_movable(String source, MoveUnit moveUnit, boolean expected) {
        // given
        Position position = Position.of(source);

        // when
        boolean movable = position.isMovable(moveUnit);

        // then
        assertThat(movable).isSameAs(expected);
    }

    @ParameterizedTest
    @CsvSource({"a2, NORTH, a3", "b7, SOUTH_WEST, a6", "e5, SOUTH_EAST_LEFT, g4"})
    @DisplayName("주어진 좌표만큼 이동한 위치를 반환한다.")
    void move(String sourceKey, MoveUnit moveUnit, String targetKey) {
        // given
        Position source = Position.of(sourceKey);
        Position target = Position.of(targetKey);

        // when
        Position result = source.move(moveUnit);

        // then
        assertThat(result).isSameAs(target);
    }
}
