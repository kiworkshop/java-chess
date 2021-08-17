package chess.domain.piece.mapper;

import chess.domain.piece.Piece;
import chess.domain.piece.Queen;

public class QueenMapper extends PieceMapper {

    private static final String NAME = "q";

    public QueenMapper() {
        super(NAME);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Queen;
    }
}
