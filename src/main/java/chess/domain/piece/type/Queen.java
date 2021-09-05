package chess.domain.piece.type;

import chess.domain.piece.move.MovePattern;
import chess.domain.player.Color;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(MovePattern.QUEEN, color);
    }
}
