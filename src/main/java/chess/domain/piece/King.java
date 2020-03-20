package chess.domain.piece;

import chess.domain.board.Position;

public class King extends ChessPiece {

    private final char name;

    public King(boolean isWhite) {
        super(isWhite);
        this.name = getName(isWhite);
    }

    public static King from(boolean isWhite) {
        return new King(isWhite);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        int rowDistance = source.rowDistance(destination);
        int columnDistance = source.columnDistance(destination);
        return rowDistance == 1 || columnDistance == 1;
    }

    @Override
    public String getMovingPolicy() {
        return null;
    }

    private char getName(boolean isWhite) {
        if (isWhite) {
            return '\u2654';
        }
        return '\u265A';
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
