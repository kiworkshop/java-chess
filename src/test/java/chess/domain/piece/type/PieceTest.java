package chess.domain.piece.type;

import chess.domain.team.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("폰인지 확인한다.")
    void is_pawn() {
        // given
        Piece pawn = new Pawn(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

        // when
        boolean isPawn = pawn.isPawn();
        boolean isNotPawn = rook.isNotPawn();

        // then
        assertThat(isPawn).isTrue();
        assertThat(isNotPawn).isTrue();
    }

    @Test
    @DisplayName("킹인지 확인한다.")
    void is_king() {
        // given
        Piece pawn = new Pawn(Color.WHITE);
        Piece king = new King(Color.BLACK);

        // when
        boolean isKing = king.isKing();
        boolean isNotKing = pawn.isKing();

        // then
        assertThat(isKing).isTrue();
        assertThat(isNotKing).isFalse();
    }
}
