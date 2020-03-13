import ChessPieces.ChessPiecePosition;
import ChessPieces.ChessPieces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;


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

        chessPieces.move(ChessPiecePosition.getPositionByArray(1,2), ChessPiecePosition.getPositionByArray(1,3));

        Object toPositionPiece = chessPieces.getPieceByPosition(toPosition);

        assertThat(fromPositionPiece).isEqualTo(toPositionPiece);
        assertThat(chessPieces.getPieceByPosition(fromPosition)).isNull();
    }
}