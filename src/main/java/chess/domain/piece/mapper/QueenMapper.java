package chess.domain.piece.mapper;

import chess.domain.piece.Piece;
import chess.domain.piece.Queen;

public class QueenMapper extends PieceMapper {

    private static final String NAME = "q";
    private static final double SCORE = 9;

    public QueenMapper() {
        super(NAME, SCORE);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Queen;
    }
}
