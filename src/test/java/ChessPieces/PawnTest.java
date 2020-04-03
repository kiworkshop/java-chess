package ChessPieces;

import ChessGame.ChessPieces.ChessPiecePosition;
import ChessGame.ChessPieces.ChessPieces;
import ChessGame.Exception.NotRightMoveException;
import ChessGame.PlayerNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Test
    @DisplayName("Make pawns move")
    void validatePawnMoveTest() throws Exception {
        ChessPieces chessPieces = ChessPieces.makeTestSetting();
        // 사선 잡기
        assertThatThrownBy(() -> {
            chessPieces.getPieceByPosition(ChessPiecePosition.getPositionByArray(4,6)).validateEachPieceMove(chessPieces, ChessPiecePosition.getPositionByArray(3,6), ChessPiecePosition.getPositionByArray(4,5));
        }).doesNotThrowAnyException();
        // 정면 잡기(실패)
        assertThatThrownBy(() -> {
            chessPieces.getPieceByPosition(ChessPiecePosition.getPositionByArray(4,6)).validateEachPieceMove(chessPieces, ChessPiecePosition.getPositionByArray(4,6), ChessPiecePosition.getPositionByArray(4,5));
        }).isInstanceOf(NotRightMoveException.class);

        ChessPieces chessPieces2 = ChessPieces.makeInitialSetting();
        // 백 최초 두칸 이동
        assertThatThrownBy(() -> {
            chessPieces2.move(PlayerNumber.PLAYER_NUMBER_ONE, ChessPiecePosition.getPositionByArray(1, 2), ChessPiecePosition.getPositionByArray(1, 4));
        }).isInstanceOf(NotRightMoveException.class);
        // 흑 최초 두칸 이동
        assertThatThrownBy(() -> {
            chessPieces2.move(PlayerNumber.PLAYER_NUMBER_TWO, ChessPiecePosition.getPositionByArray(1, 7), ChessPiecePosition.getPositionByArray(1, 6));
        }).isInstanceOf(NotRightMoveException.class);
        chessPieces2.move(PlayerNumber.PLAYER_NUMBER_TWO, ChessPiecePosition.getPositionByArray(2, 1), ChessPiecePosition.getPositionByArray(3, 3));
    }
}