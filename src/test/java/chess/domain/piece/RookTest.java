package chess.domain.piece;

import chess.domain.plate.File;
import chess.domain.plate.Rank;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    @Test
    @DisplayName("Rook의 이동 가능한 좌표를 확인한다")
    void checkRookMovable() {
        //given
        Rook rook = new Rook(Team.BLACK, new PiecePosition(File.A, Rank.EIGHT));

        //when//then
        assertThat(rook.movable(new PiecePosition(File.A, Rank.THREE))).isTrue();
        assertThat(rook.movable(new PiecePosition(File.B, Rank.EIGHT))).isTrue();
        assertThat(rook.movable(new PiecePosition(File.C, Rank.SEVEN))).isFalse();
    }
}
