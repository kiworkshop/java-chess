package chess.domain.piece;

public class King extends Piece {

    public King(final Color color) {
        super(color);
    }

    @Override
    protected void validatePattern(int fileGap, int rankGap) {
        if (!isFiniteStraight(fileGap, rankGap) && !isFiniteDiagonal(fileGap, rankGap)) {
            throw new IllegalArgumentException("킹이 이동할 수 없는 위치입니다.");
        }
    }
}
