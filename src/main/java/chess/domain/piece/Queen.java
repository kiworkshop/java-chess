package chess.domain.piece;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    protected void validatePattern(int fileGap, int rankGap) {
        if (!isDiagonal(fileGap, rankGap) && !isFiniteStraight(fileGap, rankGap)) {
            throw new IllegalArgumentException("퀸이 이동할 수 없는 위치입니다.");
        }
    }
}
