package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    private Board board;

    @BeforeEach
    void before() {
        board = Board.of(BoardInitializer.reset());
    }

    @Test
    @DisplayName("보드판 생성을 테스트 한다.")
    void create() {
        //then
        assertThat(board).isInstanceOf(Board.class);
        assertThat(board.values()).hasSize(64);
    }

    @Test
    @DisplayName("보드에서 체스말의 위치를 변경한다.")
    void move() {
        //given
        Position sourcePosition = Position.from("a2");
        Position targetPosition = Position.from("a4");

        //when
        board.move(board.from(sourcePosition), board.from(targetPosition));

        //then
        assertThat(board.from(targetPosition)).isInstanceOf(Pawn.class);
        assertThat(board.from(sourcePosition)).isInstanceOf(Blank.class);
    }
}
