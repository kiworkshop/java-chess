package chess.domain.piece;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(MovePattern.DIAGONAL_COORDINATES, color, true);
    }
}
