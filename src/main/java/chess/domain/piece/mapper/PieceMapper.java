package chess.domain.piece.mapper;

import chess.domain.piece.Piece;

public abstract class PieceMapper {
    private final String name;

    PieceMapper(final String name) {
        this.name = name;
    }

    abstract boolean supports(Piece piece);

    public String findNameBy(Piece piece) {
        if (piece.isWhite()) {
            return name;
        }

        return name.toUpperCase();
    }
}
