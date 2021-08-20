package chess.domain.piece;

import chess.domain.piece.type.MovePattern;

public class King extends Piece {

    public King(final Color color) {
        super(MovePattern.kingPattern(), color);
    }
}
