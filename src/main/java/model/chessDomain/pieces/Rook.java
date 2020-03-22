package model.chessDomain.pieces;

import model.chessDomain.ChessBoardSnapshot;
import model.chessDomain.Position;

public class Rook extends ChessPiece {
    public Rook(PieceColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean isValidMove(Position source, Position destination, ChessBoardSnapshot boardSnapshot) {
        // check if move is valid (vertical move)
        if (source.getX() == destination.getX()) {
            // check if piece is on the way
            int numGridBetween = Math.abs(source.getY() - destination.getY()) - 1;
            int yStep = (destination.getY() - source.getY()) / Math.abs(destination.getY() - source.getY());
            for (int i = 1; i <= numGridBetween; i++) {
                Position position = Position.of(source.getX(), source.getY() + yStep * i);
                if (boardSnapshot.get(position) != null) {
                    return false;
                }
            }
            return true;
        }

        // check if move is valid (horizontal move)
        if (source.getY() == destination.getY()) {
            // check if piece is on the way
            int numGridBetween = Math.abs(source.getX() - destination.getX()) - 1;
            int xStep = (destination.getX() - source.getX()) / Math.abs(destination.getX() - source.getX());
            for (int i = 1; i <= numGridBetween; i++) {
                Position position = Position.of(source.getX() + xStep * i, source.getY());
                if (boardSnapshot.get(position) != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
