package chess.domain.piece;

public class King extends Piece {

    public King(final Color color) {
        super(color);
    }

    @Override
    protected void validatePattern(final int fileGap, final int rankGap) {

    }
}
