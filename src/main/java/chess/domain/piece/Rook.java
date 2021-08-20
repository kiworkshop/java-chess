package chess.domain.piece;

import chess.domain.piece.type.MovePattern;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(MovePattern.rookPattern(), color);
    }
}
