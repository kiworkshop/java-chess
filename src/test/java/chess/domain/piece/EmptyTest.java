package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyTest {

    private Movable empty;

    @BeforeEach
    void setUp() {
        empty = Empty.create();
    }

    @Test
    @DisplayName("emptyChess는 움직일 수 없음")
    void emptyChessCantMove() {
        assertThat(empty.canMove(Position.from("A1"), Position.from("A2"))).isFalse();
    }

    @Test
    @DisplayName("움직이는 조건 설명")
    void getMovingStrategyForUser() {
        assertThat(empty.getMovingPolicy()).contains("비어있는");
    }

    @Test
    @DisplayName("비어있는 체스 판")
    void getTostring() {
        assertThat(empty.toString()).isEqualTo("[ ]");
    }
}