package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PawnTest {

    @ParameterizedTest
    @CsvSource({"b2, b3, WHITE", "b2, a3, WHITE", "b2, c3, WHITE",
            "b7, b6, BLACK", "b7, a6, BLACK", "b7, c6, BLACK"})
    @DisplayName("최초 이동 시 1칸 전진한다.")
    void find_paths_success_move_count_one_on_initial_move(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when
        Set<Position> paths = piece.findPath(source, target);

        //then
        assertThat(paths).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"b2, b4, WHITE, b3", "b7, b5, BLACK, b6"})
    @DisplayName("최초 이동시 2칸 전진하면 지나가는 경로를 반환한다.")
    void find_paths_success_move_count_two_on_initial_move(String sourcePosition, String targetPosition, Color color, String expected) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when
        Set<Position> paths = piece.findPath(source, target);

        //then
        assertThat(paths).containsOnly(Position.of(expected));
    }

    @ParameterizedTest
    @CsvSource({"d4, d5, WHITE", "d4, c5, WHITE", "d4, e5, WHITE",
            "d4, d3, BLACK", "d4, c3, BLACK", "d4, e3, BLACK"})
    @DisplayName("최초 이동이 아닌 경우 1칸 전진한다.")
    void find_paths_success_move_count_one(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when
        Set<Position> paths = piece.findPath(source, target);

        //then
        assertThat(paths).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({"d2, d5, WHITE", "d7, d4, BLACK"})
    @DisplayName("최초 이동 시 2칸 초과 전진하면 예외가 발생한다.")
    void find_paths_fail_move_invalid_count_on_initial_move(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findPath(source, target));
    }

    @ParameterizedTest
    @CsvSource({"d4, d6, WHITE", "d4, c6, WHITE", "d4, e6, WHITE",
            "d4, d2, BLACK", "d4, c2, BLACK", "d4, e2, BLACK"})
    @DisplayName("최초 이동이 아닌 경우 1칸 초과 전진하면 예외가 발생한다.")
    void find_paths_fail_move_invalid_count(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findPath(source, target));
    }

    @ParameterizedTest
    @CsvSource({"d2, d1, WHITE", "d7, d8, BLACK"})
    @DisplayName("후진 시 예외가 발생한다.")
    void find_paths_fail_move_backward(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findPath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position position = Position.of("d4");
        Piece pawn = new Pawn(Color.WHITE);
        Collection<Position> expected = Arrays.asList(
                Position.of("c5"), Position.of("e5")
        );

        //when
        Collection<Position> availableAttackPositions = pawn.findAvailableAttackPositions(position);

        //then
        assertThat(availableAttackPositions)
                .hasSize(expected.size())
                .containsAll(expected);
    }
}
