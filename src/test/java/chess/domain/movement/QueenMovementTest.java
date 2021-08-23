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

import static org.assertj.core.api.Assertions.assertThat;

class QueenMovementTest {
    private Board board;

    @BeforeEach
    void before() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.board();
    }

    @Test
    @DisplayName("퀸의 초기 위치에서 이동 규칙에 따라 이동 가능한 모든 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece queen = board.from(Position.from("d1"));
        List<Position> exceptPosition = Arrays.asList(Position.from("a1"), Position.from("b1"), Position.from("c1"),
                Position.from("e1"), Position.from("f1"), Position.from("g1"), Position.from("h1"),
                Position.from("d2"), Position.from("d3"), Position.from("d4"), Position.from("d5"),
                Position.from("d6"), Position.from("d7"), Position.from("d8"), Position.from("c2"),
                Position.from("b3"), Position.from("a4"), Position.from("e2"), Position.from("f3"),
                Position.from("g4"), Position.from("h5"));

        //when
        List<Position> movablePositions = queen.getMovablePositions();

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }
}
