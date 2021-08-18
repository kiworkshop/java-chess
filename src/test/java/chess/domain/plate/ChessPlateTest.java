package chess.domain.plate;

import chess.domain.piece.File;
import chess.domain.piece.PiecePosition;
import chess.domain.piece.Rank;
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

    @Test
    @DisplayName("직선 이동 범위 한칸 전의 경로까지 말이 있는지 확인")
    void testCheckHavePieceOnStraightPath() {
        //given
        ChessPlate chessPlate = new ChessPlate();

        boolean result = chessPlate.havePieceOnStraightPath(new PiecePosition(File.E, Rank.EIGHT), new PiecePosition(File.E, Rank.SIX));

        //when,then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("대각선 이동 범위 한칸 전의 경로까지 말이 있는지 확인")
    void testCheckHavePieceOnDiagonalPath() {
        //given
        ChessPlate chessPlate = new ChessPlate();

        boolean result = chessPlate.havePieceOnStraightPath(new PiecePosition(File.E, Rank.EIGHT), new PiecePosition(File.C, Rank.SIX));

        //when,then
        assertThat(result).isFalse();
    }
}
