package chess.domain.piece;

import chess.domain.piece.type.MovePattern;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(MovePattern.bishopPattern(), color);
    }
}
