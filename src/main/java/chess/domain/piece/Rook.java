package chess.domain.piece;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    protected void validatePattern(final int fileGap, final int rankGap) {
        if (!isStraight(fileGap, rankGap)) {
            throw new IllegalArgumentException("룩이 이동할 수 없는 위치입니다.");
        }
    }
}
