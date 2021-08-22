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

public class RookMovementTest {
    private Board board;

    @BeforeEach
    void before() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.board();
    }

    @Test
    @DisplayName("룩의 이동 규칙에 따라 종,횡으로 이동 가능한 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece rook = board.from(Position.from("a1"));
        List<Position> exceptPosition = Arrays.asList(Position.from("a2"), Position.from("a3"), Position.from("a4"),
                Position.from("a5"), Position.from("a6"), Position.from("a7"),
                Position.from("a8"), Position.from("b1"), Position.from("c1"),
                Position.from("d1"), Position.from("e1"), Position.from("f1"),
                Position.from("g1"), Position.from("h1"));

        //when
        List<Position> movablePositions = rook.getMovablePositions();

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }
}
