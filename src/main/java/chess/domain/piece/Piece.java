package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;

public abstract class Piece {
    private final Color color;

    Piece(final Color color) {
        this.color = color;
    }

    public boolean isWhite() {
        return WHITE == color;
    }
}
