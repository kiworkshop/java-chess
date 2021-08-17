package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Set;

import static chess.domain.piece.Color.WHITE;

public abstract class Piece {
    private final Color color;

    Piece(final Color color) {
        this.color = color;
    }

    public boolean isWhite() {
        return WHITE == color;
    }

    public boolean hasSameColor(final Piece target) {
        return this.color == target.color;
    }

    public abstract Set<Position> findPaths(final Position source, final Position target);
}
