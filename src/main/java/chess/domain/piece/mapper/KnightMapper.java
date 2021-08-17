package chess.domain.piece.mapper;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;

public class KnightMapper extends PieceMapper {

    private static final String NAME = "n";

    public KnightMapper() {
        super(NAME);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Knight;
    }
}
