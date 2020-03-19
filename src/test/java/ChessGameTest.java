import ChessGame.ChessPieces.ChessPiecePosition;
import ChessGame.ChessPieces.ChessPieces;
import ChessGame.Exception.CannotJumptException;
import ChessGame.PlayerNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ChessGameTest {

    @BeforeEach
    void setUp() {
        ChessPieces chessPieces = ChessPieces.makeInitialSetting();
    }

    @Test
    @DisplayName("움직여라")
    void testMove() throws Exception {   //Pawn으로 실험
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
        ChessPieces chessPieces = ChessPieces.makeTestSetting();
        assertThatThrownBy(() -> {chessPieces.move(PlayerNumber.PLAYER_NUMBER_ONE, ChessPiecePosition.getPositionByArray(4,5), ChessPiecePosition.getPositionByArray(4,7));}).isInstanceOf(CannotJumptException.class);
        assertThatThrownBy(() -> {chessPieces.move(PlayerNumber.PLAYER_NUMBER_ONE, ChessPiecePosition.getPositionByArray(4,5), ChessPiecePosition.getPositionByArray(2,5));}).isInstanceOf(CannotJumptException.class);
        assertThatThrownBy(() -> {chessPieces.move(PlayerNumber.PLAYER_NUMBER_ONE, ChessPiecePosition.getPositionByArray(4,5), ChessPiecePosition.getPositionByArray(6,7));}).isInstanceOf(CannotJumptException.class);
        assertThatThrownBy(() -> {chessPieces.move(PlayerNumber.PLAYER_NUMBER_ONE, ChessPiecePosition.getPositionByArray(4,5), ChessPiecePosition.getPositionByArray(6,3));}).isInstanceOf(CannotJumptException.class);
    }
}