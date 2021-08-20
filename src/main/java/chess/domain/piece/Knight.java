package chess.domain.piece;

import chess.domain.piece.pattern.MovePattern;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(MovePattern.knightPattern(), color);
    }
}
