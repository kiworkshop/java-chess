package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.game.ChessGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnMovementTest {
    private ChessGame chessGame;
    private Board board;

    @BeforeEach
    void before() {
        chessGame = ChessGame.of(new Ready());
        ChessGame start = chessGame.start();
        board = start.board();
    }

    @Test
    @DisplayName("폰의 초기 위치에서 이동 규칙에 따라 이동 가능한 모든 위치를 반환한다.")
    void movable_positions() {
        //given
        Piece pawn = board.from(Position.from("f2"));
        List<Position> exceptPosition = Arrays.asList(Position.from("f3"));

        //when
        List<Position> movablePositions = pawn.getMovablePositions();

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }

    @ParameterizedTest
    @CsvSource(value = {"f2, f4", "f7, f5"})
    @DisplayName("첫 수인 경우 2칸을 이동할 수 있다.")
    void first_move_positions(String source, String target) {
        //given
        Piece pawn = board.from(Position.from(source));

        //when
        boolean canMove = pawn.canMove(pawn, board.from(Position.from(target)));

        //then
        assertThat(canMove).isTrue();
    }
}
