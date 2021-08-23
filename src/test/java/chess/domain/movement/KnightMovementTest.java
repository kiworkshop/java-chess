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

class KnightMovementTest {

    private Board board;

    @BeforeEach
    void before() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.board();
    }

    @Test
    @DisplayName("나이트의 초기 위치에서 이동 규칙에 따라 이동 가능한 모든 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece knight = board.from(Position.from("b1"));
        List<Position> exceptPosition = Arrays.asList(Position.from("a3"), Position.from("c3"), Position.from("d2"));

        //when
        List<Position> movablePositions = knight.getMovablePositions();

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }

}
