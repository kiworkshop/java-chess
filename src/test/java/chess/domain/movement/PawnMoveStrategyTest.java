package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PawnMoveStrategyTest {
    private static Board board;

    @BeforeAll
    static void beforeAll() {
        ChessGame chessGame = ChessGame.of(new Ready());
        board = chessGame.getBoard();
    }

    @ParameterizedTest
    @MethodSource({"pawnFirstMove"})
    @Order(1)
    @DisplayName("처음 이동하는 경우 폰이 이동할 수 있는 위치를 반환한다.")
    void first_movable_positions(Position sourcePosition, List<Position> exceptedPositions) {
        //given
        Piece pawn = board.from(sourcePosition);

        //when
        Set<Position> movablePositions = pawn.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }

    public static Stream<Arguments> pawnFirstMove() {
        return Stream.of(
                Arguments.of(Position.from("a2"), Arrays.asList(Position.from("a3"), Position.from("a4"))),
                Arguments.of(Position.from("b2"), Arrays.asList(Position.from("b3"), Position.from("b4"))),
                Arguments.of(Position.from("c2"), Arrays.asList(Position.from("c3"), Position.from("c4"))),
                Arguments.of(Position.from("d2"), Arrays.asList(Position.from("d3"), Position.from("d4"))),
                Arguments.of(Position.from("e2"), Arrays.asList(Position.from("e3"), Position.from("e4"))),
                Arguments.of(Position.from("f2"), Arrays.asList(Position.from("f3"), Position.from("f4"))),
                Arguments.of(Position.from("g2"), Arrays.asList(Position.from("g3"), Position.from("g4"))),
                Arguments.of(Position.from("h2"), Arrays.asList(Position.from("h3"), Position.from("h4"))),
                Arguments.of(Position.from("a7"), Arrays.asList(Position.from("a6"), Position.from("a5"))),
                Arguments.of(Position.from("b7"), Arrays.asList(Position.from("b6"), Position.from("b5"))),
                Arguments.of(Position.from("c7"), Arrays.asList(Position.from("c6"), Position.from("c5"))),
                Arguments.of(Position.from("d7"), Arrays.asList(Position.from("d6"), Position.from("d5"))),
                Arguments.of(Position.from("e7"), Arrays.asList(Position.from("e6"), Position.from("e5"))),
                Arguments.of(Position.from("f7"), Arrays.asList(Position.from("f6"), Position.from("f5"))),
                Arguments.of(Position.from("g7"), Arrays.asList(Position.from("g6"), Position.from("g5"))),
                Arguments.of(Position.from("h7"), Arrays.asList(Position.from("h6"), Position.from("h5")))
        );
    }

    @Test
    @Order(2)
    @DisplayName("폰이 첫 수가 아닌 경우 2칸을 이동할 수 없다.")
    void not_first_move_positions() {
        //given
        board.move(board.from(Position.from("f2")), board.from(Position.from("f3")));
        board.move(board.from(Position.from("e7")), board.from(Position.from("e6")));
        Piece whitePawn = board.from(Position.from("f3"));
        Piece blackPawn = board.from(Position.from("e6"));

        //when //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> whitePawn.canMove(board, board.from(Position.from("f5"))))
                .withMessage("체스말을 옮길 수 있는 위치가 아닙니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> blackPawn.canMove(board, board.from(Position.from("e4"))))
                .withMessage("체스말을 옮길 수 있는 위치가 아닙니다.");
    }

    @Test
    @Order(3)
    @DisplayName("대각선에 상대 말이 있는 경우 폰이 이동할 수 있는 위치를 반환한다.")
    void attack_movable_positions() {
        //given
        board.move(board.from(Position.from("c2")), board.from(Position.from("c4")));
        board.move(board.from(Position.from("d7")), board.from(Position.from("d5")));
        Piece pawn = board.from(Position.from("c4"));
        List<Position> exceptedPositions = Arrays.asList(Position.from("c5"), Position.from("d5"));

        //when
        Set<Position> movablePositions = pawn.getMovablePositions(board);

        //then
        assertThat(movablePositions.size()).isEqualTo(exceptedPositions.size());
        exceptedPositions.forEach(exceptedPosition ->
                assertThat(movablePositions.contains(exceptedPosition)).isTrue());
    }
}
