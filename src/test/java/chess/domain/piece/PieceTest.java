package chess.domain.piece;

import chess.domain.board.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @ParameterizedTest
    @MethodSource({"generatePiece"})
    @DisplayName("초기 위치에 체스 말을 생성한다.")
    void createTest(Piece piece, Position position) {
        //given //when //then
        assertThat(piece.position()).isEqualTo(position);
    }

    @Test
    @DisplayName("체스 말이 입력 받은 위치로 이동한다.")
    void move() {
        //given
        Piece knight = Knight.of(Team.WHITE, Position.from("b1"));

        //when
        knight.move(Position.from("c3"));

        //then
        assertThat(knight.position()).isEqualTo(Position.from("c3"));
    }

    private static Stream<Arguments> generatePiece() {
        return TestPiece.generate();
    }
}
