package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;

public class Queen extends ChessPiece {

    public Queen(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        super(chessTeam, chessBoardPosition);
    }

    public static ChessPiece from(ChessTeam chessTeam, int nthRow) {
        int nthColumn = chessTeam.getPawnNthColumn();
        ChessBoardPosition chessBoardPosition = ChessBoardPosition.from(nthRow, nthColumn);
        return new Pawn(chessTeam, chessBoardPosition);
    }

    @Override
    public boolean canMove(ChessBoardPosition targetPosition) {
        return isSameRow(targetPosition) && isOneStepAbove(targetPosition);
    }

    private boolean isSameRow(ChessBoardPosition targetPosition) {
        return this.chessBoardPosition.getNthRow() == targetPosition.getNthRow();
    }

    private boolean isOneStepAbove(ChessBoardPosition targetPosition) {
        return this.chessBoardPosition.getNthColumn() == targetPosition.getNthColumn() + 1;
    }
}
