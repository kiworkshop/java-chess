package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.move.Path;
import chess.domain.player.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class KnightTest {

    @ParameterizedTest
    @ValueSource(strings = {"c6", "e6", "c2", "e2", "f5", "f3", "b5", "b3"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success(String targetPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Knight(Color.WHITE);
        Path expected = new Path(Collections.emptyList());

        //when
        Path path = piece.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c3", "c4", "c5", "e3", "e4", "e5", "d3", "d5"})
    @DisplayName("도착 위치가 이동할 수 없는 경로일 경우 예외가 발생한다.")
    void find_paths_invalid_target(String invalidTarget) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(invalidTarget);
        Piece piece = new Knight(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> piece.findMovePath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position source = Position.of("d4");
        Piece knight = new Knight(Color.WHITE);
        Collection<Path> expected = Arrays.asList(
                new Path(source, Position.of("c6")), new Path(source, Position.of("c2")), new Path(source, Position.of("e6")), new Path(source, Position.of("e2")),
                new Path(source, Position.of("b5")), new Path(source, Position.of("b3")), new Path(source, Position.of("f5")), new Path(source, Position.of("f3"))
        );

        //when
        Collection<Path> availableAttackPositions = knight.findAttackPaths(source);

        //then
        assertThat(availableAttackPositions)
                .hasSize(expected.size())
                .containsAll(expected);
    }
}
