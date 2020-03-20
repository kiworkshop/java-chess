package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    Movable whitePawn;
    Movable blackPawn;

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.from(true);
        blackPawn = Pawn.from(false);
    }

    @Test
    @DisplayName("pawn은 한 row씩만 이동 가능")
    void pawnOnlyCanMoveOneRow() {
        assertThat(whitePawn.canMove(Position.from("A1"), Position.from("A2"))).isTrue();
        assertThat(blackPawn.canMove(Position.from("A1"), Position.from("A3"))).isFalse();
    }

    @Test
    @DisplayName("pawn은 옆으로 움직일 수 없음")
    void pawnOnlyCantMoveColumn() {
        assertThat(whitePawn.canMove(Position.from("A1"), Position.from("B1"))).isFalse();
    }

    @Test
    void pawnName() {
        assertThat(whitePawn.toString()).isEqualTo("[" + "♙" + "]");
        assertThat(blackPawn.toString()).isEqualTo("[" + "♟" + "]");
    }
}