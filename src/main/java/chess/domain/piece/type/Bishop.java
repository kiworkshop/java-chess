package chess.domain.piece.type;

import chess.domain.piece.move.MovePattern;
import chess.domain.team.Color;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(MovePattern.BISHOP, color);
    }
}
