package chess.domain.piece.type;

import chess.domain.piece.move.MovePattern;
import chess.domain.team.Color;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(MovePattern.ROOK, color);
    }
}
