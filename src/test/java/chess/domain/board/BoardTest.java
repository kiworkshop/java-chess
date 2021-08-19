package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BoardTest {

    @Test
    @DisplayName("보드판 생성을 테스트 한다.")
    void create() {
        //given

        //when
        Board board = Board.of(BoardInitializer.reset());

        //then
        assertThat(board).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("보드에서 체스말의 위치를 변경한다.")
    void move() {
        //given
        Position sourcePosition = Position.from("a2");
        Position targetPosition = Position.from("a4");
        Board board = Board.of(BoardInitializer.reset());

        //when
        board.move(sourcePosition, targetPosition);

        //then
        assertThat(board.from(targetPosition)).isInstanceOf(Pawn.class);
        assertThat(board.from(sourcePosition)).isInstanceOf(Blank.class);
    }

    @Test
    @DisplayName("보드 상에 이동할 체스말이 없는 경우 예외가 발생한다.")
    void validateSource() {
        //given
        Board board = Board.of(BoardInitializer.reset());

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(Position.from("a5"), Position.from("a6")));
    }
}
