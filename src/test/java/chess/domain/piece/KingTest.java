package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"c3", "c4", "c5", "e3", "e4", "e5", "d3", "d5"})
    @DisplayName("출발과 도착 위치가 주어지면 지나가는 경로를 반환한다.")
    void find_paths_success_straight(String targetPosition) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(targetPosition);
        Piece piece = new King(Color.WHITE);

        //when
        Set<Position> paths = piece.findPaths(source, target);

        //then
        assertThat(paths).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"c2", "d2", "e2", "f2", "b3", "b4", "b5"})
    @DisplayName("도착 위치가 이동할 수 없는 경로일 경우 예외가 발생한다.")
    void find_paths_invalid_target(String invalidTarget) {
        //given
        Position source = Position.of("d4");
        Position target = Position.of(invalidTarget);
        Piece piece = new King(Color.WHITE);

        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> piece.findPaths(source, target));
    }
}
