package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceResolverTest {

    @Test
    @DisplayName("피스에 해당하는 피스 타입을 반환한다.")
    void of() {
        // given
        Piece piece = new Pawn(Color.WHITE);

        // when
        PieceResolver pieceResolver = PieceResolver.of(piece);

        // then
        assertThat(pieceResolver).isSameAs(PieceResolver.PAWN);
    }

    @ParameterizedTest
    @MethodSource("createParamsForName")
    @DisplayName("피스가 주어지면 해당 피스와 색상에 맞는 이름을 반환한다.")
    void find_name_by(Piece piece, String expected) {
        //given, when
        String name = PieceResolver.findNameBy(piece);

        //then
        assertThat(name).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("createParamsForScore")
    @DisplayName("피스가 주어지면 해당 피스의 점수를 반환한다.")
    void find_score_by(Piece piece, double expected) {
        //given, when
        double score = PieceResolver.findScoreBy(piece);

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
