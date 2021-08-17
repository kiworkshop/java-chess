package chess.domain.piece.mapper;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceMappersTest {

    @ParameterizedTest
    @MethodSource("createParams")
    @DisplayName("피스가 주어지면 해당 피스와 색상에 맞는 이름을 반환한다.")
    void find(Piece piece, String expected) {
        //given, when
        String name = PieceMappers.findNameBy(piece);

        //then
        assertThat(name).isEqualTo(expected);

    }

    private static Stream<Arguments> createParams() {
        return Stream.of(
                Arguments.of(new Pawn(Color.WHITE), "p"),
                Arguments.of(new Pawn(Color.BLACK), "P")
        );
    }
}
