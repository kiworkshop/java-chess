package chess.domain.piece.pattern;

import chess.domain.piece.Color;
import chess.domain.piece.MoveCoordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static chess.domain.piece.MoveCoordinate.BLACK_PAWN_INITIAL_SOUTH;
import static chess.domain.piece.MoveCoordinate.NORTH;
import static chess.domain.piece.MoveCoordinate.NORTH_EAST;
import static chess.domain.piece.MoveCoordinate.NORTH_WEST;
import static chess.domain.piece.MoveCoordinate.SOUTH;
import static chess.domain.piece.MoveCoordinate.SOUTH_EAST;
import static chess.domain.piece.MoveCoordinate.SOUTH_WEST;
import static chess.domain.piece.MoveCoordinate.WHITE_PAWN_INITIAL_NORTH;
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
                .containsExactlyInAnyOrder(
                        BLACK_PAWN_INITIAL_SOUTH,
                        SOUTH_EAST,
                        SOUTH_WEST,
                        SOUTH);
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
                .containsExactlyInAnyOrder(
                        WHITE_PAWN_INITIAL_NORTH,
                        NORTH_EAST,
                        NORTH_WEST,
                        NORTH);
    }
}
