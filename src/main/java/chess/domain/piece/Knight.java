package chess.domain.piece;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(MovePattern.KNIGHT_COORDINATES, color, false);
    }
}
