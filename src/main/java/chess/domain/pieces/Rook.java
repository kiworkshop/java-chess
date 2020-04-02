package chess.domain.pieces;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;
import chess.domain.ChessTeam;

public class Rook extends ChessPiece{

    public Rook(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        super(chessTeam, chessBoardPosition);
    }

    public static ChessPiece from(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        return new Rook(chessTeam, chessBoardPosition);
    }

    @Override
    public boolean canMove(ChessBoardPosition targetPosition) {
        return true;
    }
}
