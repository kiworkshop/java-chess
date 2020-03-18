import ChessGame.ChessPieces.ChessPiecePosition;
import ChessGame.ChessPieces.ChessPieces;
import ChessGame.PlayerNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ChessGameTest {

    @BeforeEach
    void setUp() {
        ChessPieces chessPieces = ChessPieces.makeInitialSetting();
    }

    @Test
    @DisplayName("움직여라")
    void testMove() {   //Pawn으로 실험
        ChessPieces chessPieces = ChessPieces.makeInitialSetting();
        ChessPiecePosition fromPosition = ChessPiecePosition.getPositionByArray(1,2);
        ChessPiecePosition toPosition = ChessPiecePosition.getPositionByArray(1,3);

        Object fromPositionPiece = chessPieces.getPieceByPosition(fromPosition);

        chessPieces.move(PlayerNumber.PLAYER_NUMBER_ONE, ChessPiecePosition.getPositionByArray(1,2), ChessPiecePosition.getPositionByArray(1,3));

        Object toPositionPiece = chessPieces.getPieceByPosition(toPosition);

        assertThat(fromPositionPiece).isEqualTo(toPositionPiece);
        assertThat(chessPieces.getPieceByPosition(fromPosition)).isNull();
    }

    @Test
    @DisplayName("넘어서 움직여라")
    void testOverMove() {
        ChessPieces chessPieces = ChessPieces.makeInitialSetting();

    }
}