package chess.domain.piece;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    protected void validatePattern(final int fileGap, final int rankGap) {

    }
}
