package chess.domain.plate;

import chess.controller.ChessController;
import chess.domain.MovingDirection;
import chess.domain.piece.PiecePosition;
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

        boolean result = chessPlate.havePieceOnPath(new MovingDirection(new PiecePosition(File.E, Rank.EIGHT), new PiecePosition(File.E, Rank.SIX)));
        boolean result2 = chessPlate.havePieceOnPath(new MovingDirection(new PiecePosition(File.A, Rank.ONE), new PiecePosition(File.A, Rank.THREE)));
        boolean result3 = chessPlate.havePieceOnPath(new MovingDirection(new PiecePosition(File.H, Rank.SEVEN), new PiecePosition(File.H, Rank.FIVE)));

        //when,then
        assertThat(result).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isFalse();
    }

    @Test
    @DisplayName("대각선 이동 범위 한칸 전의 경로까지 말이 있는지 확인")
    void testCheckHavePieceOnDiagonalPath() {
        //given
        ChessPlate chessPlate = new ChessPlate();

        boolean result = chessPlate.havePieceOnPath(new MovingDirection(new PiecePosition(File.F, Rank.EIGHT), new PiecePosition(File.D, Rank.SIX)));
        boolean result2 = chessPlate.havePieceOnPath(new MovingDirection(new PiecePosition(File.A, Rank.TWO), new PiecePosition(File.C, Rank.FOUR)));


        //when,then
        assertThat(result).isTrue();
        assertThat(result2).isFalse();
    }

    @Test
    @DisplayName("체스말이 이동 후 이동가능여부를 리턴한다")
    void move() {
        //given
        ChessPlate chessPlate = new ChessPlate();
        //when
        String result = chessPlate.move(new PiecePosition(File.A, Rank.EIGHT), new PiecePosition(File.B, Rank.EIGHT));
        String result2 = chessPlate.move(new PiecePosition(File.G, Rank.EIGHT), new PiecePosition(File.F, Rank.SIX));
        String result3 = chessPlate.move(new PiecePosition(File.A, Rank.ONE), new PiecePosition(File.A, Rank.THREE));

        //then
        assertThat(result).isEqualTo(ChessController.REASK);
        assertThat(result2).isEqualTo(ChessController.MOVE);
        assertThat(result3).isEqualTo(ChessController.REASK);
    }

    @Test
    @DisplayName("폰이 이동 후 이동가능여부를 리턴한다")
    void testPawnMove() {
        //given
        ChessPlate chessPlate = new ChessPlate();
        //when
        String result = chessPlate.move(new PiecePosition(File.B, Rank.SEVEN), new PiecePosition(File.C, Rank.SIX));
        String result2 = chessPlate.move(new PiecePosition(File.G, Rank.EIGHT), new PiecePosition(File.F, Rank.SIX));
        //then
        assertThat(result).isEqualTo(ChessController.REASK);
        assertThat(result2).isEqualTo(ChessController.MOVE);
    }
    @Test
    @DisplayName("왕이 잡히면 END를 리턴한다")
    void testWhenKingDie() {
        //given
        ChessPlate chessPlate = new ChessPlate();
        //when
        String result = chessPlate.move(new PiecePosition(File.D, Rank.SEVEN), new PiecePosition(File.D, Rank.FIVE));
        String result2 = chessPlate.move(new PiecePosition(File.E, Rank.TWO), new PiecePosition(File.E, Rank.FOUR));
        String result3 = chessPlate.move(new PiecePosition(File.D, Rank.FIVE), new PiecePosition(File.E, Rank.FOUR));
        String result4 = chessPlate.move(new PiecePosition(File.D, Rank.EIGHT), new PiecePosition(File.D, Rank.THREE));
        String result5 = chessPlate.move(new PiecePosition(File.D, Rank.THREE), new PiecePosition(File.E, Rank.THREE));
        String result6 = chessPlate.move(new PiecePosition(File.E, Rank.THREE), new PiecePosition(File.E, Rank.ONE));

        //then
        assertThat(result6).isEqualTo(ChessController.END);
    }
}
