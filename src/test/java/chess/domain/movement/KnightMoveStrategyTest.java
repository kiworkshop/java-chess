package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KnightMoveStrategyTest {
    private Board board;

    @BeforeAll
    void beforeAll() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.getBoard();
    }

    @ParameterizedTest
    @MethodSource({"knightMove"})
    @DisplayName("나이트가 이동할 수 있는 위치를 반환한다.")
    void movable_positions(String sourcePosition, List<Position> exceptedPositions) {
        //given
        Piece knight = board.from(Position.from(sourcePosition));

        //when
        Set<Position> movablePositions = knight.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }

    public static Stream<Arguments> knightMove() {
        return Stream.of(
                Arguments.of("b1", Arrays.asList(Position.from("a3"), Position.from("c3"))),
                Arguments.of("g1", Arrays.asList(Position.from("f3"), Position.from("h3"))),
                Arguments.of("b8", Arrays.asList(Position.from("a6"), Position.from("c6"))),
                Arguments.of("g8", Arrays.asList(Position.from("f6"), Position.from("h6")))
        );
    }
}
