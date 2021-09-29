package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.move.Path;
import chess.domain.team.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PawnTest {

    @ParameterizedTest
    @CsvSource({"b2, b3, WHITE", "b7, b6, BLACK"})
    @DisplayName("최초 이동 시 1칸 전진한다.")
    void find_paths_success_move_count_one_on_initial_move(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);
        Path expected = new Path(Collections.emptyList());

        //when
        Path path = piece.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"b2, b4, WHITE, b3", "b7, b5, BLACK, b6"})
    @DisplayName("최초 이동시 2칸 전진하면 지나가는 경로를 반환한다.")
    void find_paths_success_move_count_two_on_initial_move(String sourcePosition, String targetPosition, Color color, String expectedPosition) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);
        Path expected = new Path(Position.of(expectedPosition));

        //when
        Path path = piece.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
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
        Path expected = new Path(Collections.emptyList());

        //when
        Path path = piece.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"d2, d5, WHITE", "d7, d4, BLACK"})
    @DisplayName("최초 이동 시 2칸 초과 전진하면 예외가 발생한다.")
    void find_paths_fail_move_invalid_count_on_initial_move(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> piece.findMovePath(source, target));
    }

    @ParameterizedTest
    @CsvSource({"d4, d6, WHITE", "d4, d2, BLACK"})
    @DisplayName("최초 이동이 아닌 경우 1칸 초과 전진하면 예외가 발생한다.")
    void find_paths_fail_move_invalid_count(String sourcePosition, String targetPosition, Color color) {
        //given
        Position source = Position.of(sourcePosition);
        Position target = Position.of(targetPosition);
        Piece piece = new Pawn(color);

        //when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> piece.findMovePath(source, target));
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
        assertThatIllegalArgumentException()
                .isThrownBy(() -> piece.findMovePath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position source = Position.of("d4");
        Piece pawn = new Pawn(Color.WHITE);
        Collection<Path> expected = Arrays.asList(
                new Path(source, Position.of("c5")), new Path(source, Position.of("e5"))
        );

        //when
        Collection<Path> availableAttackPaths = pawn.findAttackPaths(source);

        //then
        assertThat(availableAttackPaths)
                .hasSize(expected.size())
                .containsAll(expected);
    }
}
