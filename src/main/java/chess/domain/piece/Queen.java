package chess.domain.piece;

import chess.domain.piece.pattern.MovePattern;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(MovePattern.queenPattern(), color);
    }
}
