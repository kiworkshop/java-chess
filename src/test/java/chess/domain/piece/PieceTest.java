package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @ParameterizedTest
    @CsvSource({"WHITE, true", "BLACK, false"})
    @DisplayName("색상을 인자로 받아 객체를 생성한다.")
    void create(Color color, boolean expected) {
        //given, when
        Piece piece = new Pawn(color);

        //then
        assertThat(piece.isWhite()).isEqualTo(expected);
    }
}
