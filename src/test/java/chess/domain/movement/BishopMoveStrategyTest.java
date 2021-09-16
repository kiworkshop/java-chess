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

class BishopMoveStrategyTest {
    private Board board;

    @BeforeEach
    void beforeEach() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.getBoard();
    }

    @Test
    @DisplayName("비숍이 이동할 수 있는 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece bishop = board.from(Position.from("c1"));
        List<Position> exceptedPositions = Arrays.asList(Position.from("d2"), Position.from("e3"), Position.from("f4"),
                Position.from("g5"), Position.from("h6"));

        //when
        board.move(board.from(Position.from("d2")), board.from(Position.from("d4"))); // 비숍이 대각선으로 이동하도록 폰을 옮김
        Set<Position> movablePositions = bishop.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }
}
