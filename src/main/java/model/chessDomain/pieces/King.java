package model.chessDomain.pieces;

import model.chessDomain.ChessBoardSnapshot;
import model.chessDomain.Position;

public class King extends ChessPiece {
    public King(PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Position source, Position destination, ChessBoardSnapshot boardSnapshot) {
        if (Math.abs(source.getX() - destination.getX()) > 1
                || Math.abs(source.getY() - destination.getY()) > 1) {
            return false;
        }
        return true;
    }
}
