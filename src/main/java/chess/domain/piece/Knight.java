package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends ChessPiece {

    private final char name;

    private Knight(boolean isWhite) {
        super(isWhite);
        this.name = getName(isWhite);
    }

    public static Knight from(boolean isWhite) {
        return new Knight(isWhite);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        int rowDistance = source.rowDistance(destination);
        int columnDistance = source.columnDistance(destination);
        System.out.println("row : " + rowDistance + "col : " + columnDistance);
        return (rowDistance == 2 && columnDistance == 1) || (rowDistance == 1 && columnDistance == 2);
    }

    @Override
    public String getMovingPolicy() {
        return "나이트는 앞으로 두칸 전진 후 옆으로 한 칸 이동 가능하며 다른 기물을 뛰어 넘을 수 있습니다.";
    }

    private char getName(boolean isWhite) {
        if (isWhite) {
            return '\u2658';
        }
        return '\u265E';
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
