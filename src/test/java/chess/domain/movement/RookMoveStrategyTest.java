package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RookMoveStrategyTest {
    private Board board;

    @BeforeEach
    void beforeEach() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.getBoard();
    }

    @Test
    @DisplayName("룩이 이동할 수 있는 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece rook = board.from(Position.from("h8"));
        board.move(board.from(Position.from("h7")), board.from(Position.from("h5"))); // 룩이 직선으로 이동 가능하도록 폰을 옮김
        List<Position> exceptedPositions = Arrays.asList(Position.from("h6"), Position.from("h7"));

        //when
        Set<Position> movablePositions = rook.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }

    @Test
    @DisplayName("룩이 공격 및 이동할 수 있는 위치를 반환한다.")
    void attack_movable_positions() {
        //given
        List<Position> exceptedPositions = Arrays.asList(Position.from("a2"), Position.from("a3"),
                Position.from("a4"), Position.from("a5"), Position.from("a6"));

        Piece rook = board.from(Position.from("a1"));
        board.move(board.from(Position.from("a2")), board.from(Position.from("a4")));
        board.move(board.from(Position.from("a4")), board.from(Position.from("a5")));
        board.move(board.from(Position.from("a5")), board.from(Position.from("a6")));
        board.move(board.from(Position.from("a6")), board.from(Position.from("b7")));
        board.move(board.from(Position.from("b8")), board.from(Position.from("a6"))); // 나이트 a6

        //when
        Set<Position> movablePositions = rook.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }
}
