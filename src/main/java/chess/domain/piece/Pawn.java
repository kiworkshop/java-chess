package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends ChessPiece {

    private final char name;

    private Pawn(boolean isWhite) {
        super(isWhite);
        this.name = getName(isWhite);
    }

    public static Pawn from(boolean isWhite) {
        return new Pawn(isWhite);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        int rowDifference = source.rowDistance(destination);
        int colDifference = source.columnDistance(destination);
        if (rowDifference == 1 && colDifference == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getMovingPolicy() {
        return "폰은 처음에는 두 칸 이후에는 1칸씩 움직이며 상대 말을 잡을 때는 대각선 방향으로 이동 가능합니다.";
    }

    private char getName(boolean isWhite) {
        if (isWhite) {
            return '\u2659';
        }
        return '\u265F';
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
