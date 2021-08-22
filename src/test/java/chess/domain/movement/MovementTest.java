package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MovementTest {
    private ChessGame chessGame;
    private Board board;

    @BeforeEach
    void before() {
        Ready ready = new Ready();
        chessGame = ChessGame.of(ready, board);
        chessGame = chessGame.start();
        board = chessGame.board();
    }

    @Test
    @DisplayName("보드 상에 이동할 체스말이 없는 경우 예외가 발생한다.")
    void without_blank_board() {
        //given
        Piece source = board.from(Position.from("a5"));
        Piece target = board.from(Position.from("a6"));

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> source.canMove(target, source))
                .withMessage("지정 위치에 체스말이 없습니다.");
    }

    @Test
    @DisplayName("아군이 있는 칸에는 이동할 수 없다.")
    void without_same_team() {
        //given
        Piece rook = board.from(Position.from("a1"));
        Piece pawn = board.from(Position.from("a2"));

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(rook, pawn))
                .withMessage("아군이 있는 칸에는 이동할 수 없습니다.");
    }

}
