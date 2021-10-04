package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @ParameterizedTest
    @MethodSource({"generatePiece"})
    @DisplayName("초기 위치에 체스 말을 생성한다.")
    void createTest(Piece piece, Position position) {
        //given //when //then
        assertThat(piece.getPosition()).isEqualTo(position);
    }

    public static Stream<Arguments> generatePiece() {
        return Stream.of(
                Arguments.of(Rook.of(Team.WHITE, Position.from("a1")), Position.from("a1")),
                Arguments.of(Knight.of(Team.WHITE, Position.from("b1")), Position.from("b1")),
                Arguments.of(Bishop.of(Team.WHITE, Position.from("c1")), Position.from("c1")),
                Arguments.of(Queen.of(Team.WHITE, Position.from("d1")), Position.from("d1")),
                Arguments.of(King.of(Team.WHITE, Position.from("e1")), Position.from("e1")),
                Arguments.of(Bishop.of(Team.WHITE, Position.from("f1")), Position.from("f1")),
                Arguments.of(Knight.of(Team.WHITE, Position.from("g1")), Position.from("g1")),
                Arguments.of(Rook.of(Team.WHITE, Position.from("h1")), Position.from("h1")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("a2")), Position.from("a2")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("b2")), Position.from("b2")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("c2")), Position.from("c2")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("d2")), Position.from("d2")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("e2")), Position.from("e2")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("f2")), Position.from("f2")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("g2")), Position.from("g2")),
                Arguments.of(Pawn.of(Team.WHITE, Position.from("h2")), Position.from("h2")),
                Arguments.of(Rook.of(Team.BLACK, Position.from("a8")), Position.from("a8")),
                Arguments.of(Knight.of(Team.BLACK, Position.from("b8")), Position.from("b8")),
                Arguments.of(Bishop.of(Team.BLACK, Position.from("c8")), Position.from("c8")),
                Arguments.of(Queen.of(Team.BLACK, Position.from("d8")), Position.from("d8")),
                Arguments.of(King.of(Team.BLACK, Position.from("e8")), Position.from("e8")),
                Arguments.of(Bishop.of(Team.BLACK, Position.from("f8")), Position.from("f8")),
                Arguments.of(Knight.of(Team.BLACK, Position.from("g8")), Position.from("g8")),
                Arguments.of(Rook.of(Team.BLACK, Position.from("h8")), Position.from("h8")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("a7")), Position.from("a7")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("b7")), Position.from("b7")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("c7")), Position.from("c7")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("d7")), Position.from("d7")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("e7")), Position.from("e7")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("f7")), Position.from("f7")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("g7")), Position.from("g7")),
                Arguments.of(Pawn.of(Team.BLACK, Position.from("h7")), Position.from("h7"))
        );
    }
}
