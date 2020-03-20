package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends ChessPiece {

    private final char name;

    private Bishop(boolean isWhite) {
        super(isWhite);
        this.name = getName(isWhite);
    }

    public static Bishop from(boolean isWhite) {
        return new Bishop(isWhite);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        int rowDistance = source.rowDistance(destination);
        int columnDistance = source.columnDistance(destination);
        return rowDistance == columnDistance;
    }

    @Override
    public String getMovingPolicy() {
        return null;
    }

    private char getName(boolean isWhite) {
        if (isWhite) {
            return '\u2657';
        }
        return '\u265D';
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
