package chess.domain.piece.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceMappersTest {

    @ParameterizedTest
    @MethodSource("createParamsForName")
    @DisplayName("피스가 주어지면 해당 피스와 색상에 맞는 이름을 반환한다.")
    void find_name_by(Piece piece, String expected) {
        //given, when
        String name = PieceMappers.findNameBy(piece);

        //then
        assertThat(name).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createParamsForScore")
    @DisplayName("피스가 주어지면 해당 피스의 점수를 반환한다.")
    void get_score(Piece piece, double expected) {
        //given, when
        double score = PieceMappers.findScoreBy(piece);

        //then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> createParamsForName() {
        return Stream.of(
                Arguments.of(new Pawn(Color.WHITE), "p"),
                Arguments.of(new Pawn(Color.BLACK), "P")
        );
    }

    private static Stream<Arguments> createParamsForScore() {
        return Stream.of(
                Arguments.of(new Pawn(Color.WHITE), 1),
                Arguments.of(new Knight(Color.WHITE), 2.5),
                Arguments.of(new Bishop(Color.WHITE), 3),
                Arguments.of(new Rook(Color.WHITE), 5),
                Arguments.of(new Queen(Color.WHITE), 9)
        );
    }
}
