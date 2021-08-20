package chess.domain.piece;

import chess.domain.piece.pattern.MovePattern;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(MovePattern.rookPattern(), color);
    }
}
