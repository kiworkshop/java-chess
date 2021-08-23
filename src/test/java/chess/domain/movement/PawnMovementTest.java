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

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }

    @ParameterizedTest
    @CsvSource(value = {"f2, f4", "f7, f5"})
    @DisplayName("첫 수인 경우 2칸을 이동할 수 있다.")
    void first_move_positions(String source, String target) {
        //given
        Piece pawn = board.from(Position.from(source));
        List<Position> exceptPosition = Arrays.asList(Position.from(target));

        //when
        List<Position> movablePositions = pawn.getMovablePositions();

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }

    @ParameterizedTest
    @CsvSource(value = {"f3, f5", "e6, e4"})
    @DisplayName("첫 수가 아닌 경우 2칸을 이동할 수 없다.")
    void not_first_move_positions(String source, String target) {
        //given
        Piece pawn = board.from(Position.from(source));

        //when
        List<Position> movablePositions = pawn.getMovablePositions();

        //then
        assertThat(movablePositions).isNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"f2, e3", "f2, g3", "f7, e6", "f7, g6"})
    @DisplayName("대각선 1칸 앞에 적의 기물이 있는 경우 기물을 뺏고 그 자리로 이동할 수 있다.")
    void attack_positions(String source, String target) {
        //given
        Piece pawn = board.from(Position.from(source));
        List<Position> exceptPosition = Arrays.asList(Position.from(target));

        //when
        List<Position> movablePositions = pawn.getMovablePositions();

        //then
        movablePositions.forEach(movable -> assertThat(exceptPosition.contains(movable)).isTrue());
    }
}
