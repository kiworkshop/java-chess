package chess.model.domain.game.pieces;

import chess.model.domain.game.board.ChessBoardSnapshot;
import chess.model.domain.game.Position;

public class King extends ChessPiece {
    public King(PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean isValidMove(ChessBoardSnapshot boardSnapshot, Position source, Position destination) {
        if (Math.abs(source.getX() - destination.getX()) > 1
                || Math.abs(source.getY() - destination.getY()) > 1) {
            return false;
        }
        return true;
    }
}
