package chess.domain.piece;

import chess.domain.plate.File;
import chess.domain.plate.Rank;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    @Test
    @DisplayName("Bishop의 이동 가능한 좌표를 확인한다")
    void checkKingMovable() {
        //given
        Bishop bishop = new Bishop(Team.BLACK, new PiecePosition(File.E, Rank.EIGHT));

        //when//then
        assertThat(bishop.canMoveTo(new PiecePosition(File.D, Rank.SEVEN))).isTrue();
        assertThat(bishop.canMoveTo(new PiecePosition(File.D, Rank.EIGHT))).isFalse();
        assertThat(bishop.canMoveTo(new PiecePosition(File.E, Rank.SEVEN))).isFalse();
    }
}
