package chess.domain.pieces;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;
import chess.domain.ChessTeam;

public class Knight extends ChessPiece{

    public Knight(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        super(chessTeam, chessBoardPosition);
    }

    public static Knight from(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        return new Knight(chessTeam, chessBoardPosition);
    }

    @Override
    public boolean canMove(ChessBoardPosition targetPosition) {
        return true;
    }
}
