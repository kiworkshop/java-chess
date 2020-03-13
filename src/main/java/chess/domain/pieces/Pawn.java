package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;

public class Pawn extends ChessPiece {

    public Pawn(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        super(chessTeam, chessBoardPosition);
    }

    // Todo 검증 매우 중요.. black : top
    public static ChessPiece from(ChessTeam chessTeam, int nthRow) {
        int nthColumn = chessTeam.getPawnNthColumn();
        ChessBoardPosition chessBoardPosition = ChessBoardPosition.from(nthRow, nthColumn);
        return new Pawn(chessTeam, chessBoardPosition);
    }

    public void move(ChessBoardPosition targetPosition) {

        this.chessBoardPosition = targetPosition;
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