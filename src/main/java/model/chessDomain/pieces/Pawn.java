package model.chessDomain.pieces;

import model.chessDomain.ChessBoardSnapshot;
import model.chessDomain.Position;

public class Pawn extends ChessPiece {
    public Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isValidMove(Position source, Position destination, ChessBoardSnapshot boardSnapshot) {
        // 2 steps at the first move for WHITE
        if (boardSnapshot.get(source).getColor() == PieceColor.WHITE && source.getY() == 1
                && source.getX() == destination.getX() && destination.getY() == 3
                && boardSnapshot.get(Position.of(source.getX(), 2)) == null
                && boardSnapshot.get(destination) == null) {
            return true;
        }
        // 2 steps at the first move for BLACK
        if (boardSnapshot.get(source).getColor() == PieceColor.BLACK && source.getY() == 6
                && source.getX() == destination.getX() && destination.getY() == 4
                && boardSnapshot.get(Position.of(source.getX(), 5)) == null
                && boardSnapshot.get(destination) == null) {
            return true;
        }

        // move 1 step for WHITE
        if (boardSnapshot.get(destination) == null && boardSnapshot.get(source).getColor() == PieceColor.WHITE
                && source.getX() == destination.getX() && source.getY() + 1 == destination.getY()) {
            return true;
        }
        // move 1 step for BLACK
        if (boardSnapshot.get(destination) == null && boardSnapshot.get(source).getColor() == PieceColor.BLACK
                && source.getX() == destination.getX() && source.getY() - 1 == destination.getY()) {
            return true;
        }

        // kill for WHITE
        if (boardSnapshot.get(destination) != null && boardSnapshot.get(source).getColor() == PieceColor.WHITE
                && Math.abs(source.getX() - destination.getX()) == 1 && source.getY() + 1 == destination.getY()) {
            return true;
        }
        // kill for BLACK
        if (boardSnapshot.get(destination) != null && boardSnapshot.get(source).getColor() == PieceColor.BLACK
                && Math.abs(source.getX() - destination.getX()) == 1 && source.getY() - 1 == destination.getY()) {
            return true;
        }

        return false;
    }
}
