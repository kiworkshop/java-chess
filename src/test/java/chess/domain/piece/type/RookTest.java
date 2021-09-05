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

public class RookTest {

    @ParameterizedTest
    @CsvSource({"d2, d3", "d6, d5", "b4, c4", "f4, e4"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success(String targetPosition, String expectedPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new Rook(Color.WHITE);
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
        Piece piece = new Rook(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findMovePath(source, target));
    }

    @Test
    @DisplayName("입력받은 위치에서 공격 가능한 위치들을 반환해준다.")
    void find_available_attack_positions() {
        //given
        Position source = Position.of("d4");
        Piece rook = new Rook(Color.WHITE);
        Collection<Path> expected = Arrays.asList(
                new Path(Position.of("d3"), Position.of("d2"), Position.of("d1")),
                new Path(Position.of("d5"), Position.of("d6"), Position.of("d7"), Position.of("d8")),
                new Path(Position.of("c4"), Position.of("b4"), Position.of("a4")),
                new Path(Position.of("e4"), Position.of("f4"), Position.of("g4"), Position.of("h4"))
        );

        //when
        Collection<Path> availableAttackPaths = rook.findAttackPaths(source);

        //then
        assertThat(availableAttackPaths)
                .hasSize(expected.size())
                .containsAll(expected);
    }
}
