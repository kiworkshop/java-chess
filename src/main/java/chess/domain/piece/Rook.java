package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends ChessPiece {

    private final char name;

    private Rook(boolean isWhite) {
        super(isWhite);
        this.name = getName(isWhite);
    }

    public static Rook from(boolean isWhite) {
        return new Rook(isWhite);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        int rowDistance = source.rowDistance(destination);
        int columnDistance = source.columnDistance(destination);
        boolean moveStrategy = (rowDistance != 0 && columnDistance == 0) || (rowDistance == 0 && columnDistance != 0);
        return moveStrategy;
    }

    @Override
    public String getMovingPolicy() {
        return "룩은 상하좌우로만 움직일 수 있으면 앞에 기물이 있을 경우 넘어갈 수 없습니다.";
    }

    private char getName(boolean isWhite) {
        if (isWhite) {
            return '\u2656';
        }
        return '\u265C';
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
