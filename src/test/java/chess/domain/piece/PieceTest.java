package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    @DisplayName("Piece의 생성을 테스트한다.")
    void createTest() {
        //given
        Piece knight = Knight.of(Team.WHITE, Position.of("b1"));

        //when

        //then
        assertThat(knight.position()).isEqualTo(Position.of("b1"));
    }
}
