package chess.domain.piece.mapper;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class PawnMapper extends PieceMapper {

    private static final String NAME = "p";

    public PawnMapper() {
        super(NAME);
    }

    @Override
    public boolean supports(final Piece piece) {
        return piece instanceof Pawn;
    }
}
