package chess.domain.pieces;

import chess.domain.ChessBoardPosition;
import chess.domain.ChessPiece;
import chess.domain.ChessTeam;

public class King extends ChessPiece {

    public King(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        super(chessTeam, chessBoardPosition);
    }

    public static King from(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        return new King(chessTeam, chessBoardPosition);
    }

    @Override
    public boolean canMove(ChessBoardPosition targetPosition) {
        return true;
    }

}
