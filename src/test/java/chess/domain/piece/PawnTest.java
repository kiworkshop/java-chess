package chess.domain.piece;

import chess.domain.plate.File;
import chess.domain.plate.Rank;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @Test
    @DisplayName("폰은 처음 위치에서 2칸 움직일 수 있다")
    void testPawnCanMoveTwoBlockAtFirstMove() {
        //given
        Pawn pawn = new Pawn(Team.BLACK, new PiecePosition(File.C, Rank.SEVEN));

        //when

        //then
        assertThat(pawn.movable(new PiecePosition(File.C, Rank.FIVE))).isTrue();
    }

    @Test
    @DisplayName("폰은 1칸 전진할 수 있다")
    void testPawnCanMoveOneBlock() {
        //given
        Pawn pawn = new Pawn(Team.BLACK, new PiecePosition(File.C, Rank.FIVE));

        //when

        //then
        assertThat(pawn.movable(new PiecePosition(File.C, Rank.FOUR))).isTrue();
        assertThat(pawn.movable(new PiecePosition(File.C, Rank.SIX))).isFalse();
    }

    @Test
    @DisplayName("폰은 공격할 때 전진방향 대각선으로만 공격할 수 있다")
    void pawnMovableDiagonal() {
        //given
        Pawn blackPawn = new Pawn(Team.BLACK, new PiecePosition(File.C, Rank.SEVEN));
        Pawn whitePawn = new Pawn(Team.WHITE, new PiecePosition(File.D, Rank.TWO));

        //when

        //then
        assertThat(blackPawn.movable(new PiecePosition(File.D, Rank.SIX))).isTrue();
        assertThat(blackPawn.movable(new PiecePosition(File.B, Rank.SIX))).isTrue();
        assertThat(whitePawn.movable(new PiecePosition(File.C, Rank.THREE))).isTrue();
        assertThat(whitePawn.movable(new PiecePosition(File.E, Rank.THREE))).isTrue();
    }
}
