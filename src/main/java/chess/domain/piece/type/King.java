package chess.domain.piece.type;

import chess.domain.piece.move.MovePattern;
import chess.domain.player.Color;

public class King extends Piece {

    public King(final Color color) {
        super(MovePattern.KING, color);
    }
}
