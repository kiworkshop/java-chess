package chess.domain.piece;

import chess.domain.piece.pattern.MovePattern;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(MovePattern.bishopPattern(), color);
    }
}
