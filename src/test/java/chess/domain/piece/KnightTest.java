package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    void move() {
        Movable knight = Knight.from(true);
        boolean canMove = knight.canMove(Position.from("B8"), Position.from("A5"));
        assertThat(canMove).isTrue();
    }
}