package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class KingMoveStrategyTest {
    private Board board;

    @BeforeEach
    void beforeEach() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.getBoard();
    }

    @Test
    @DisplayName("킹이 이동할 수 있는 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece king = board.from(Position.from("e1"));
        board.move(board.from(Position.from("d2")), board.from(Position.from("d4"))); // 킹이 대각선으로 이동하도록 폰을 옮김
        board.move(board.from(Position.from("e2")), board.from(Position.from("e4"))); // 킹이 직선으로 이동하도록 폰을 옮김
        board.move(board.from(Position.from("f2")), board.from(Position.from("f4"))); // 킹이 대각선으로 이동하도록 폰을 옮김
        List<Position> exceptedPositions = Arrays.asList(Position.from("d2"), Position.from("e2"), Position.from("f2"));

        //when
        Set<Position> movablePositions = king.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }

    @Test
    @DisplayName("체크메이트 되는 위치로는 이동할 수 없다.")
    void checkmate_positions() {
        //given
        Piece king = board.from(Position.from("e1"));
        moveCheckmatePosition();
        Piece checkmatePosition = board.from(Position.from("e2"));

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> king.canMove(board, checkmatePosition))
                .withMessage("체크메이트 위치로 이동할 수 없습니다.");
    }

    private void moveCheckmatePosition() {
        board.move(board.from(Position.from("e2")), board.from(Position.from("e4")));
        board.move(board.from(Position.from("d7")), board.from(Position.from("d5")));
        board.move(board.from(Position.from("c8")), board.from(Position.from("g4")));
    }
}
