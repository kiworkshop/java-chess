package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn implements Movable {

    private final boolean isWhite;
    private final char name;

    public Pawn(boolean isWhite) {
        this.isWhite = isWhite;
        this.name = getName(isWhite);
    }

    @Override
    public boolean canMove(Position prev, Position next) {
        int rowDifference = prev.rowDistance(next);
        int colDifference = prev.colDistance(next);
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
