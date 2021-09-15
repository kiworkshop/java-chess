package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.move.Path;
import chess.domain.team.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class QueenTest {

    @ParameterizedTest
    @CsvSource({"b6, c5", "b2, c3", "f2, e3", "f6, e5"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success_diagonal(String targetPosition, String expectedPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Queen(Color.WHITE);
        Path expected = new Path(Position.of(expectedPosition));

        //when
        Path path = piece.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"d5", "c4", "e4", "d3"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success_cardinal(String targetPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Queen(Color.WHITE);
        Path expected = new Path(Collections.emptyList());

        //when
        Path paths = piece.findMovePath(source, target);

        //then
        assertThat(paths).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f5", "e6", "b3", "c2"})
    @DisplayName("도착 위치가 이동할 수 없는 경로일 경우 예외가 발생한다.")
    void find_paths_invalid_target(String invalidTarget) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(invalidTarget);
        Piece piece = new Queen(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> piece.findMovePath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position source = Position.of("d4");
        Piece queen = new Queen(Color.WHITE);
        Collection<Path> expected = Arrays.asList(
                // diagonal
                new Path(Position.of("c3"), Position.of("b2"), Position.of("a1")),
                new Path(Position.of("e5"), Position.of("f6"), Position.of("g7"), Position.of("h8")),
                new Path(Position.of("c5"), Position.of("b6"), Position.of("a7")),
                new Path(Position.of("e3"), Position.of("f2"), Position.of("g1")),
                // cardinal
                new Path(Position.of("d3"), Position.of("d2"), Position.of("d1")),
                new Path(Position.of("d5"), Position.of("d6"), Position.of("d7"), Position.of("d8")),
                new Path(Position.of("c4"), Position.of("b4"), Position.of("a4")),
                new Path(Position.of("e4"), Position.of("f4"), Position.of("g4"), Position.of("h4"))
        );

        //when
        Collection<Path> availableAttackPaths = queen.findAttackPaths(source);

        //then
        assertThat(availableAttackPaths)
                .hasSize(expected.size())
                .containsAll(expected);
    }
}
