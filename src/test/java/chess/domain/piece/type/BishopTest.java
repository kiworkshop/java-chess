package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.move.Path;
import chess.domain.player.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BishopTest {

    @ParameterizedTest
    @CsvSource({"b6, c5", "b2, c3", "f2, e3", "f6, e5"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success(String targetPosition, String expectedPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Bishop(Color.WHITE);
        Path expected = new Path(Position.of(expectedPosition));

        //when
        Path path = piece.findMovePath(source, target);

        //then
        assertThat(path).isEqualTo(expected);
    }

    @Test
    @DisplayName("도착 위치가 이동할 수 없는 경로일 경우 예외가 발생한다.")
    void find_paths_invalid_target() {
        //given
        Position source = Position.of("c1");
        Position target = Position.of("f5");
        Piece piece = new Bishop(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findMovePath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position position = Position.of("d4");
        Piece bishop = new Bishop(Color.WHITE);
        Collection<Path> expected = Arrays.asList(
                new Path(Position.of("c3"), Position.of("b2"), Position.of("a1")),
                new Path(Position.of("e5"), Position.of("f6"), Position.of("g7"), Position.of("h8")),
                new Path(Position.of("c5"), Position.of("b6"), Position.of("a7")),
                new Path(Position.of("e3"), Position.of("f2"), Position.of("g1"))
        );

        //when
        Collection<Path> availableAttackPaths = bishop.findAttackPaths(position);

        //then
        assertThat(availableAttackPaths)
                .hasSize(expected.size())
                .containsAll(expected);
    }
}
