package chess.domain.piece.mapper;

import chess.domain.piece.Piece;

public abstract class PieceMapper {
    private final String name;
    private final double score;

    PieceMapper(final String name, final double score) {
        this.name = name;
        this.score = score;
    }

    abstract boolean supports(Piece piece);

    public String findNameBy(Piece piece) {
        if (piece.isWhite()) {
            return name;
        }

        return name.toUpperCase();
    }

    public double getScore() {
        return score;
    }
}
