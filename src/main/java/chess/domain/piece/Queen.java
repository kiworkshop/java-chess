package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> findPaths(final Position source, final Position target) {
        return null;
    }
}
