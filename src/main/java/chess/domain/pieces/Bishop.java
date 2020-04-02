package chess.domain.pieces;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;
import chess.domain.ChessTeam;

public class Bishop extends ChessPiece {

    public Bishop(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        super(chessTeam, chessBoardPosition);
    }

    public static ChessPiece from(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        return new Bishop(chessTeam, chessBoardPosition);
    }

    @Override
    public boolean canMove(ChessBoardPosition targetPosition) {
        return true;
    }

}
