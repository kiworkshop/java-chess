package chess.domain.piece.type;

import chess.domain.piece.move.MovePattern;
import chess.domain.team.Color;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(MovePattern.KNIGHT, color);
    }
}
