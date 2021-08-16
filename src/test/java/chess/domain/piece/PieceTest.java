package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("Piece의 생성을 테스트한다.")
    void createTest() {
        //given
        Piece knight = Knight.of(new Position(File.B, Rank.ONE), Player.WHITE);

        //when
        //then
        assertThat(knight.getPosition()).isEqualTo(new Position(File.B, Rank.ONE));

    }
}
