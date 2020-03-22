package model.chessDomain.pieces;

import model.chessDomain.ChessBoardSnapshot;
import model.chessDomain.Position;

public class Knight extends ChessPiece {
    public Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean isValidMove(Position source, Position destination, ChessBoardSnapshot boardSnapshot) {
        if (Math.abs(source.getX() - destination.getX()) == 1
                && Math.abs(source.getY() - destination.getY()) == 2) {
            return true;
        } else if (Math.abs(source.getX() - destination.getX()) == 2
                && Math.abs(source.getY() - destination.getY()) == 1) {
            return true;
        }
        return false;
    }
}
