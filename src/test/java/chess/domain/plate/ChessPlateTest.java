package chess.domain.plate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPlateTest {

    @Test
    @DisplayName("체스판 생성하고 포함된 객체 개수 확인")
    void testCreateChessPlate() {
        //given
        ChessPlate chessPlate = new ChessPlate();

        //when,then
        assertThat(chessPlate.getAllPieces()).hasSize(32);
    }
}
