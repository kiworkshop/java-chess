package chess.domain.piece;

import chess.domain.piece.pattern.MovePattern;

public class King extends Piece {

    public King(final Color color) {
        super(MovePattern.kingPattern(), color);
    }
}
