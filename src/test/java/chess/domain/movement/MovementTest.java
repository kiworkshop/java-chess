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

class MovementTest {
    private ChessGame chessGame;
    private Board board;

    @BeforeEach
    void before() {
        chessGame = ChessGame.of(new Ready());
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
                .isThrownBy(() -> source.canMove(board, target, source))
                .withMessage("지정 위치에 체스말이 없습니다.");
    }

    @Test
    @DisplayName("아군이 있는 칸에는 이동할 수 없다.")
    void without_same_team() {
        //given
        Position rook = Position.from("a1");
        Position pawn = Position.from("a2");

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.moveAndToggleTurn(rook, pawn))
                .withMessage("아군이 있는 칸에는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("다른 체스 말을 뛰어넘을 수 없다.")
    void disable_jump() {
        //given
        Position rook = Position.from("a1");
        Position a3 = Position.from("a3");

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.moveAndToggleTurn(rook, a3))
                .withMessage("다른 체스 말을 뛰어넘을 수 없습니다.");
    }
}
