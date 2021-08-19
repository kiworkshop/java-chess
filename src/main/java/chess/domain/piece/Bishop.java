package chess.domain.piece;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    protected void validatePattern(int fileGap, int rankGap) {
        if (!isDiagonal(fileGap, rankGap)) {
            throw new IllegalArgumentException("비숍이 이동할 수 없는 위치입니다.");
        }
    }
}
