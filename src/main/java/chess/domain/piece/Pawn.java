package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> findPaths(final Position source, final Position target) {
        return null;
    }
}
