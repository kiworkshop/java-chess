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

class PawnMovementTest {
    private Board board;

    @BeforeEach
    void before() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.board();
    }

    @Test
    @DisplayName("폰의 초기 위치에서 이동 규칙에 따라 이동 가능한 모든 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece pawn = board.from(Position.from("f2"));
        List<Position> exceptPosition = Arrays.asList(Position.from("f3"));

        //when
        List<Position> movablePositions = pawn.getMovablePositions();
        movablePositions.forEach(position -> System.out.println("========>   " + position.fileSymbol() + position.rankNumber()));

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }
}
