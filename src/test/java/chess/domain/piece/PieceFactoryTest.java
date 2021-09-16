package chess.domain.piece;

import chess.domain.player.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {

    @ParameterizedTest
    @CsvSource(value = {"WHITE", "BLACK"})
    @DisplayName("색상에 맞는 기물을 생성한다.")
    void createPieces(Color color) {
        //when
        Map<Position, Piece> pieces = PieceFactory.createPieces(color);

        //then
        assertThat(pieces).hasSize(16);
    }
}
