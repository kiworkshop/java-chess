package chess.domain.piece;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(MovePattern.CARDINAL_DIRECTIONS, color, true);
    }
}
