package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.player.MoveCoordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static chess.domain.player.MoveCoordinate.*;
import static org.assertj.core.api.Assertions.assertThat;

class MovePatternTest {

    @Test
    @DisplayName("색상에 따라 폰 패턴을 반환한다.")
    void pawn_pattern_black() {
        // given
        Color color = Color.BLACK;

        // when
        MovePattern movePattern = MovePattern.pawnPattern(color);
        Collection<MoveCoordinate> moveCoordinates = movePattern.finiteMoveCoordinates();

        // then
        assertThat(moveCoordinates)
                .containsExactlyInAnyOrder(BLACK_PAWN_INITIAL_SOUTH, SOUTH_EAST, SOUTH_WEST, SOUTH);
    }

    @Test
    @DisplayName("색상에 따라 폰 패턴을 반환한다.")
    void pawn_pattern_white() {
        // given
        Color color = Color.WHITE;

        // when
        MovePattern movePattern = MovePattern.pawnPattern(color);
        Collection<MoveCoordinate> moveCoordinates = movePattern.finiteMoveCoordinates();

        // then
        assertThat(moveCoordinates)
                .containsExactlyInAnyOrder(WHITE_PAWN_INITIAL_NORTH, NORTH_EAST, NORTH_WEST, NORTH);
    }
}
