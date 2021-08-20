package chess.domain.piece;

import chess.domain.piece.pattern.MovePattern;

public class Pawn extends Piece {

    private static final int VALID_GAP_ON_ATTACK = 1;
    private static final int MINIMUM_MOVE_COUNT = 1;
    private static final int MAXIMUM_MOVE_COUNT = 2;

    private boolean isInitialMove = true;

    public Pawn(final Color color) {
        super(MovePattern.pawnPattern(color), color);
    }
}
