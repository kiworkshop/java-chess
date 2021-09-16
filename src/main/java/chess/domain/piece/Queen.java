package chess.domain.piece;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(MovePattern.ALL_DIRECTIONS, color, true);
    }
}
