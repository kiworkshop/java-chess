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

class QueenMoveStrategyTest {
    private Board board;

    @BeforeEach
    void beforeEach() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.getBoard();
    }

    @Test
    @DisplayName("퀸이 이동할 수 있는 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece queen = board.from(Position.from("d1"));
        board.move(board.from(Position.from("c2")), board.from(Position.from("c4"))); // 퀸이 대각선으로 이동하도록 폰을 옮김
        board.move(board.from(Position.from("d2")), board.from(Position.from("d4"))); // 퀸이 직선으로 이동하도록 폰을 옮김
        List<Position> exceptedPositions = Arrays.asList(Position.from("d2"), Position.from("d3"),
                Position.from("c2"), Position.from("b3"), Position.from("a4"));

        //when
        Set<Position> movablePositions = queen.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }
}
