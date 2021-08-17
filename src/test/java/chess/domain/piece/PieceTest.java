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
        Piece knight = Knight.of(Team.WHITE, Position.from("b1"));

        //when

        //then
        assertThat(knight.position()).isEqualTo(Position.from("b1"));
    }

    @Test
    @DisplayName("Piece가 입력 받은 위치로 이동한다.")
    void move() {
        //given
        Piece knight = Knight.of(Team.WHITE, Position.from("b1"));

        //when
        knight.move(Position.from("c3"));

        //then
        assertThat(knight.position()).isEqualTo(Position.from("c3"));
    }
}
