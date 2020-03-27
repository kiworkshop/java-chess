package chess.model.domain.game.pieces;

import chess.model.domain.game.board.ChessBoardSnapshot;
import chess.model.domain.game.Position;

public class Bishop extends ChessPiece {
    public Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean isValidMove(ChessBoardSnapshot boardSnapshot, Position source, Position destination) {
        // check if move is valid
        if (Math.abs(source.getX() - destination.getX()) == Math.abs(source.getY() - destination.getY())) {
            // check if piece is on the way
            int numGridBetween = Math.abs(source.getX() - destination.getX()) - 1;
            int xStep = (destination.getX() - source.getX()) / Math.abs(destination.getX() - source.getX());
            int yStep = (destination.getY() - source.getY()) / Math.abs(destination.getY() - source.getY());
            for (int i = 1; i <= numGridBetween; i++) {
                Position position = Position.of(source.getX() + xStep * i, source.getY() + yStep * i);
                if (boardSnapshot.get(position) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
