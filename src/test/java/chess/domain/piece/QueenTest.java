package chess.domain.piece;

import chess.domain.plate.File;
import chess.domain.plate.Rank;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    @Test
    @DisplayName("Queen의 이동 가능한 좌표를 확인한다")
    void checkQueenMovable() {
        //given
        Queen queen = new Queen(Team.BLACK, new PiecePosition(File.D, Rank.FOUR));

        //when//then
        assertThat(queen.movable(new PiecePosition(File.D, Rank.THREE))).isTrue();
        assertThat(queen.movable(new PiecePosition(File.D, Rank.FIVE))).isTrue();
        assertThat(queen.movable(new PiecePosition(File.C, Rank.FOUR))).isTrue();
        assertThat(queen.movable(new PiecePosition(File.E, Rank.FOUR))).isTrue();
        assertThat(queen.movable(new PiecePosition(File.C, Rank.FIVE))).isTrue();
        assertThat(queen.movable(new PiecePosition(File.C, Rank.THREE))).isTrue();
        assertThat(queen.movable(new PiecePosition(File.E, Rank.FIVE))).isTrue();
        assertThat(queen.movable(new PiecePosition(File.E, Rank.THREE))).isTrue();

        assertThat(queen.movable(new PiecePosition(File.B, Rank.FIVE))).isFalse();
        assertThat(queen.movable(new PiecePosition(File.F, Rank.FIVE))).isFalse();
        assertThat(queen.movable(new PiecePosition(File.C, Rank.TWO))).isFalse();
        assertThat(queen.movable(new PiecePosition(File.C, Rank.SIX))).isFalse();

    }
}
