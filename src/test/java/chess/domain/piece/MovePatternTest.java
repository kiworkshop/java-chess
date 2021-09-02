package chess.domain.piece;

import chess.domain.player.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static chess.domain.player.Direction.NORTH;
import static chess.domain.player.Direction.NORTH_EAST;
import static chess.domain.player.Direction.NORTH_WEST;
import static chess.domain.player.Direction.SOUTH;
import static chess.domain.player.Direction.SOUTH_EAST;
import static chess.domain.player.Direction.SOUTH_WEST;
import static org.assertj.core.api.Assertions.assertThat;

class MovePatternTest {

    @Test
    @DisplayName("색상에 따라 폰 패턴을 반환한다.")
    void pawn_pattern_black() {
        // given
        Color color = Color.BLACK;
        MovePattern movePattern = MovePattern.findPawnMovePattern(color);

        // when
        Collection<Direction> directions = movePattern.getDirections();

        // then
        assertThat(directions)
                .containsExactlyInAnyOrder(SOUTH_EAST, SOUTH_WEST, SOUTH);
    }

    @Test
    @DisplayName("색상에 따라 폰 패턴을 반환한다.")
    void pawn_pattern_white() {
        // given
        Color color = Color.WHITE;
        MovePattern movePattern = MovePattern.findPawnMovePattern(color);

        // when
        Collection<Direction> directions = movePattern.getDirections();

        // then
        assertThat(directions)
                .containsExactlyInAnyOrder(NORTH_EAST, NORTH_WEST, NORTH);
    }
}
