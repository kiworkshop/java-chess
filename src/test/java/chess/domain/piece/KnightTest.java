package chess.domain.piece;

import chess.domain.plate.File;
import chess.domain.plate.Rank;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    @DisplayName("나이트의 이동범위를 확인한다")
    void testCanMoveKnight() {
        //given
        Knight knight = new Knight(Team.BLACK, new PiecePosition(File.D, Rank.FOUR));
        Knight knight1 = new Knight(Team.BLACK, new PiecePosition(File.G, Rank.EIGHT));
        //when

        //then
        assertThat(knight.movable(new PiecePosition(File.B, Rank.FIVE))).isTrue();
        assertThat(knight.movable(new PiecePosition(File.C, Rank.SIX))).isTrue();
        assertThat(knight.movable(new PiecePosition(File.E, Rank.SIX))).isTrue();
        assertThat(knight.movable(new PiecePosition(File.F, Rank.FIVE))).isTrue();
        assertThat(knight.movable(new PiecePosition(File.F, Rank.THREE))).isTrue();
        assertThat(knight.movable(new PiecePosition(File.E, Rank.TWO))).isTrue();
        assertThat(knight.movable(new PiecePosition(File.C, Rank.TWO))).isTrue();
        assertThat(knight.movable(new PiecePosition(File.B, Rank.THREE))).isTrue();
        assertThat(knight1.movable(new PiecePosition(File.F, Rank.SIX))).isTrue();

        assertThat(knight.movable(new PiecePosition(File.B, Rank.SIX))).isFalse();
        assertThat(knight.movable(new PiecePosition(File.E, Rank.FOUR))).isFalse();
    }

}