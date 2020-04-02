package chess.domain.pieces;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;
import chess.domain.ChessTeam;

public class Pawn extends ChessPiece {

    public Pawn(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        super(chessTeam, chessBoardPosition);
    }

    public static Pawn from(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        return new Pawn(chessTeam, chessBoardPosition);
    }

    @Override
    public boolean canMove(ChessBoardPosition targetPosition) {
        return true;
    }
}