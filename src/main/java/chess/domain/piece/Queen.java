package chess.domain.piece;

import chess.domain.piece.type.MovePattern;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(MovePattern.queenPattern(), color);
    }
}
