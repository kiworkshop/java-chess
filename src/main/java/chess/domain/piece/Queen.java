package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends ChessPiece {

    private final char name;

    private Queen(boolean isWhite) {
        super(isWhite);
        this.name = getName(isWhite);
    }

    public static Queen from(boolean isWhite) {
        return new Queen(isWhite);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        int rowDistance = source.rowDistance(destination);
        int columnDistance = source.columnDistance(destination);
        boolean rookStrategy = (rowDistance != 0 && columnDistance == 0) || (rowDistance == 0 && columnDistance != 0);
        boolean bishopStrategy = rowDistance == columnDistance;
        return rookStrategy || bishopStrategy;
    }

    @Override
    public String getMovingPolicy() {
        return "Queen은 상하좌우 혹은 대각선 방향으로 몇 칸이든 이동 가능합니다";
    }

    private char getName(boolean isWhite) {
        if (isWhite) {
            return '\u2655';
        }
        return '\u265B';
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
